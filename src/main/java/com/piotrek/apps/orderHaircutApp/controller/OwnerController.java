package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonOpeningHoursDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.enums.Days;
import com.piotrek.apps.orderHaircutApp.services.HairSalonOpeningHoursService;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final HairSalonService hairSalonService;

    private final UserService userService;

    private final HairSalonOpeningHoursService hairSalonOpeningHoursService;

    public OwnerController(HairSalonService hairSalonService, UserService userService, HairSalonOpeningHoursService hairSalonOpeningHoursService) {
        this.hairSalonService = hairSalonService;
        this.userService = userService;
        this.hairSalonOpeningHoursService = hairSalonOpeningHoursService;
    }

    @GetMapping("/showOwnerPanel")
    public String showOwnerPanel() {
        return "owner-panel";
    }

    @GetMapping("/showSalonAddPage")
    public String showSalonAddPage(Model model) {

        HairSalonDto hairSalonDto = new HairSalonDto();

        populateDays(hairSalonDto);

        model.addAttribute("hairSalonDto", hairSalonDto);
        return "hair-salon-add-page";
    }

    private void populateDays(HairSalonDto hairSalonDto) {
        for (Days dayName : Days.values()) {
            HairSalonOpeningHoursDto tempDay = new HairSalonOpeningHoursDto();
            tempDay.setDayName(dayName.toString());
            tempDay.setDayNameForm(dayName);
            hairSalonDto.addDay(tempDay);
        }
    }

    @PostMapping("/processHairSalonAdd")
    public String processHairSalonAdd(
            @Valid @ModelAttribute("hairSalonDto") HairSalonDto hairSalonDto,
            BindingResult bindingResult, Model model,
            Authentication authentication) throws ParseException {

        if (bindingResult.hasErrors()) {
            fillDaysForForm(hairSalonDto);
            return "hair-salon-add-page";
        }

        if(hairSalonDto.getSalonOpenHour().equals("") || hairSalonDto.getSalonCloseHour().equals("")){
            fillDaysForForm(hairSalonDto);
            model.addAttribute("hoursError", "Podaj prawidłowe godziny");
            return "hair-salon-add-page";
        }

        DateFormat df = new SimpleDateFormat("HH:mm");
        if ((df.parse(hairSalonDto.getSalonCloseHour()).getTime() - df.parse(hairSalonDto.getSalonOpenHour()).getTime()) <= 0) {
            fillDaysForForm(hairSalonDto);
            model.addAttribute("hoursError", "Godzina otwarcia salonu musi być mniejsza niż godzina zamknięcia");
            return "hair-salon-add-page";
        }

        List<HairSalonOpeningHoursDto> hairSalonOpeningHoursList = new ArrayList<>();

        for (HairSalonOpeningHoursDto day : hairSalonDto.getHairSalonOpeningHoursDtoList()) {
            if (day.getDayName() != null) {
                day.setSalonOpenHour(hairSalonDto.getSalonOpenHour());
                day.setSalonCloseHour(hairSalonDto.getSalonCloseHour());
                hairSalonOpeningHoursList.add(day);
            } else {
                HairSalonOpeningHours tempHours = hairSalonOpeningHoursService.get(day.getId());
                hairSalonOpeningHoursService.delete(tempHours);
            }
        }
        hairSalonDto.setHairSalonOpeningHoursDtoList(hairSalonOpeningHoursList);
        hairSalonService.save(hairSalonDto, authentication);

        return "owner-panel";
    }

    private void fillDaysForForm(HairSalonDto hairSalonDto) {
        int i = 0;
        for (Days dayName : Days.values()) {
            hairSalonDto.getHairSalonOpeningHoursDtoList().get(i).setDayNameForm(dayName);
            i++;
        }
    }

    @Transactional
    @GetMapping("/showOwnerSalons")
    public String showOwnerSalons(Model model, Authentication authentication) {

        User user = userService.findByUserName(authentication.getName());

        List<HairSalon> hairSalons = hairSalonService.findAllByUser(user);
        for (HairSalon hairSalon : hairSalons) {
            Hibernate.initialize(hairSalon.getHairSalonOpeningHoursList());
            Collections.sort(hairSalon.getHairSalonOpeningHoursList());
        }

        model.addAttribute("hairSalons", hairSalons);
        return "owner-hair-salon-list-page";
    }

    @GetMapping("/deleteSalon")
    @Transactional
    public String deleteHairSalon(@RequestParam(value = "id") int id) {
//        HairSalon tempSalon = hairSalonService.get(id);
//        hairSalonService.delete(tempSalon);
        hairSalonService.deleteById(id);
        return "redirect:/owner/showOwnerSalons";
    }

    @Transactional
    @GetMapping("/editSalon")
    public String editHairSalon(@RequestParam(value = "id") int id, Model model) {
        HairSalon tempSalon = hairSalonService.get(id);
        HairSalonDto hairSalonDto = new HairSalonDto();
        Hibernate.initialize(tempSalon.getHairSalonOpeningHoursList());

        for (Days dayName : Days.values()) {
            HairSalonOpeningHoursDto tempDay = new HairSalonOpeningHoursDto();
            tempDay.setDayNameForm(dayName);
            for (HairSalonOpeningHours day : tempSalon.getHairSalonOpeningHoursList()) {
                if (dayName.toString().equals(day.getDayName())) {
                    tempDay.setId(day.getId());
                    tempDay.setDayName(day.getDayName());
                    tempDay.setSalonOpenHour(day.getSalonOpenHour());
                    tempDay.setSalonCloseHour(day.getSalonCloseHour());
                }
            }
            hairSalonDto.getHairSalonOpeningHoursDtoList().add(tempDay);
        }
        hairSalonDto.setId(tempSalon.getId());
        hairSalonDto.setSalonName(tempSalon.getSalonName());
        hairSalonDto.setCity(tempSalon.getCity());
        hairSalonDto.setSalonAddress(tempSalon.getSalonAddress());
        hairSalonDto.setPhoneNumber(tempSalon.getPhoneNumber());
        hairSalonDto.setSalonOpenHour(hairSalonDto.getHairSalonOpeningHoursDtoList().get(0).getSalonOpenHour());
        hairSalonDto.setSalonCloseHour(hairSalonDto.getHairSalonOpeningHoursDtoList().get(0).getSalonCloseHour());

        model.addAttribute("hairSalonDto", hairSalonDto);

        return "hair-salon-add-page";
    }
}
