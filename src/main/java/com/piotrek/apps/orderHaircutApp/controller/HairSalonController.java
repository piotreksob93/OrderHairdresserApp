package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonOpeningHoursDto;
import com.piotrek.apps.orderHaircutApp.dto.HairdresserDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.entity.HairdresserRating;
import com.piotrek.apps.orderHaircutApp.enums.Days;
import com.piotrek.apps.orderHaircutApp.services.HairSalonOpeningHoursService;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.HairdresserService;
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
import java.util.List;

@Controller
@RequestMapping("/hairsalon")
public class HairSalonController {

    private final HairSalonService hairSalonService;


    private final HairSalonOpeningHoursService hairSalonOpeningHoursService;

    private final HairdresserService hairdresserService;

    public HairSalonController(HairSalonService hairSalonService, HairSalonOpeningHoursService hairSalonOpeningHoursService, HairdresserService hairdresserService) {
        this.hairSalonService = hairSalonService;
        this.hairSalonOpeningHoursService = hairSalonOpeningHoursService;
        this.hairdresserService = hairdresserService;
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

        if (hairSalonDto.getSalonOpenHour().equals("") || hairSalonDto.getSalonCloseHour().equals("")) {
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

    @GetMapping("/deleteSalon")
    @Transactional
    public String deleteHairSalon(@RequestParam(value = "id") int id) {
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

    @RequestMapping("/manageSalon")
    public String showHairSalonManagePage(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("id", id);
        return "salon-management-page";
    }

    @Transactional
    @RequestMapping("/salonHairdressers")
    public String showHairSalonHairdressers(@RequestParam(value = "id") int id, Model model) {
        HairSalon hairSalon = hairSalonService.get(id);
        List<Hairdresser> hairdresserList = hairdresserService.findAllByHairSalon(hairSalon);
        List<HairdresserDto> hairdresserDtoList = new ArrayList<>();
        for (Hairdresser hairdresser : hairdresserList) {
            HairdresserDto hairdresserDto = new HairdresserDto();
            hairdresserDto.setId(hairdresser.getId());
            hairdresserDto.setFirstName(hairdresser.getFirstName());
            hairdresserDto.setLastName(hairdresser.getLastName());
            List<HairdresserRating> ratings = hairdresser.getHairdresserRatings();
            float sum = 0;
            if (ratings.size() != 0) {
                for (HairdresserRating rating : ratings) {
                    sum += rating.getRating();
                }
                hairdresserDto.setAverageRating(sum / ratings.size());
            } else {
                hairdresserDto.setAverageRating(sum);
            }
            hairdresserDto.setHairdresserRatings(hairdresser.getHairdresserRatings());
            hairdresserDto.setHairdresserRatings(hairdresser.getHairdresserRatings());
            hairdresserDto.setHairSalon(hairdresser.getHairSalon());
            hairdresserDto.setHairServiceOnReservations(hairdresser.getHairServiceOnReservations());
            hairdresserDto.setHairServices(hairdresser.getHairServices());
            hairdresserDtoList.add(hairdresserDto);
        }

        model.addAttribute("id", id);
        model.addAttribute("salonName", hairSalon.getSalonName());
        model.addAttribute("hairdresserDtoList", hairdresserDtoList);
        return "hair-salon-hairdressers-list";
    }

    @RequestMapping("/salonServices")
    @Transactional
    public String showSalonServices(@RequestParam(value = "id") int id, Model model){
        HairSalon hairSalon = hairSalonService.get(id);
        Hibernate.initialize(hairSalon.getHairServices());
        List<HairService> hairServices = hairSalon.getHairServices();

        model.addAttribute("hairSalon", hairSalon);
        model.addAttribute("hairServices",hairServices);

        return "hair-salon-services-list";
    }

}
