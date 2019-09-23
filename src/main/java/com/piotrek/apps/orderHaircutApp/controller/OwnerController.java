package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String showOwnerPanel(){
        return "owner-panel";
    }

    @GetMapping("/showSalonAddPage")
    public String showSalonAddPage(Model model){
        model.addAttribute("hairSalonDto",new HairSalonDto());
//        String minute = null;
//        model.addAttribute("minute",minute);
        return "hair-salon-add-page";
    }


    @PostMapping("/processHairSalonAdd")
    public String processHairSalonAdd(
            @ModelAttribute ("hairSalonDto") HairSalonDto hairSalonDto,
            Authentication authentication,
            @ModelAttribute ("minute") String minute){

//        if (bindingResult.hasErrors()) {
//            return "hair-salon-add-page";
//        }
        System.out.println(minute);
        hairSalonService.save(hairSalonDto,authentication);

        return "owner-panel";
    }


    @GetMapping("/showOWnerSalons")
    public String showOWnerSalons(Model model,Authentication authentication){

        User user = userService.findByUserName(authentication.getName());

        List<HairSalon> hairSalons = hairSalonService.findAllByUser(user);

        model.addAttribute("hairSalons",hairSalons);
        return "owner-hair-salon-list-page";
    }
}
