package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.services.HairSalonOpeningHoursService;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.HairdresserService;
import com.piotrek.apps.orderHaircutApp.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final HairSalonService hairSalonService;

    private final UserService userService;

    private final HairSalonOpeningHoursService hairSalonOpeningHoursService;

    private final HairdresserService hairdresserService;

    public OwnerController(HairSalonService hairSalonService, UserService userService, HairSalonOpeningHoursService hairSalonOpeningHoursService, HairdresserService hairdresserService) {
        this.hairSalonService = hairSalonService;
        this.userService = userService;
        this.hairSalonOpeningHoursService = hairSalonOpeningHoursService;
        this.hairdresserService = hairdresserService;
    }

    @GetMapping("/showOwnerPanel")
    public String showOwnerPanel() {
        return "owner-panel";
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
}
