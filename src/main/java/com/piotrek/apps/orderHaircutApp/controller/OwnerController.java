package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonOpeningHoursWrapper;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.enums.Days;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final HairSalonService hairSalonService;

    private final UserService userService;

    public OwnerController(HairSalonService hairSalonService, UserService userService) {
        this.hairSalonService = hairSalonService;
        this.userService = userService;
    }

    @GetMapping("/showOwnerPanel")
    public String showOwnerPanel() {
        return "owner-panel";
    }

    @GetMapping("/showSalonAddPage")
    public String showSalonAddPage(Model model) {

        HairSalonOpeningHoursWrapper hairSalonOpeningHoursWrapper = new HairSalonOpeningHoursWrapper();

        int i = 0;
        for (Days dayName : Days.values()) {
            HairSalonOpeningHours tempDay = new HairSalonOpeningHours();
//            tempDay.setDay(Integer.toString(i + 1));
            tempDay.setDayName(dayName.toString());
            hairSalonOpeningHoursWrapper.addDay(tempDay);
            i++;
        }

        model.addAttribute("hairSalonDto", new HairSalonDto());
        model.addAttribute("openingHours", new HairSalonOpeningHours());
        model.addAttribute("daysOfWeek", hairSalonOpeningHoursWrapper);
        return "hair-salon-add-page";
    }

    @PostMapping("/processHairSalonAdd")
    public String processHairSalonAdd(
            @ModelAttribute("hairSalonDto") HairSalonDto hairSalonDto,
            @ModelAttribute("openingHours") HairSalonOpeningHours hairSalonOpeningHours,
            @ModelAttribute("daysOfWeek") HairSalonOpeningHoursWrapper daysOfWeek,
            Authentication authentication) {

        List<HairSalonOpeningHours> hairSalonOpeningHoursList = new ArrayList<>();

        for (HairSalonOpeningHours day : daysOfWeek.getDays()) {
            if (day.getDayName() != null) {
                day.setSalonOpenHour(hairSalonOpeningHours.getSalonOpenHour());
                day.setSalonCloseHour(hairSalonOpeningHours.getSalonCloseHour());
                hairSalonOpeningHoursList.add(day);
            }
        }
        hairSalonDto.setHairSalonOpeningHoursList(hairSalonOpeningHoursList);
        hairSalonService.save(hairSalonDto, authentication);

        return "owner-panel";
    }

    @Transactional
    @GetMapping("/showOwnerSalons")
    public String showOwnerSalons(Model model, Authentication authentication) {

        User user = userService.findByUserName(authentication.getName());

        List<HairSalon> hairSalons = hairSalonService.findAllByUser(user);
        for (HairSalon hairSalon : hairSalons) {
            Hibernate.initialize(hairSalon.getHairSalonOpeningHoursList());
        }

        model.addAttribute("hairSalons", hairSalons);
        return "owner-hair-salon-list-page";
    }
}
