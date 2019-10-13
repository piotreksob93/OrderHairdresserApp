package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairdresserDto;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.HairdresserService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/hairdresser")
public class HairdresserController {

    private final HairSalonService hairSalonService;
    private final HairdresserService hairdresserService;

    public HairdresserController(HairSalonService hairSalonService, HairdresserService hairdresserService) {
        this.hairSalonService = hairSalonService;
        this.hairdresserService = hairdresserService;
    }

    @RequestMapping("/addHairdresser")
    public String showHairdresserAddPage(@RequestParam(value = "id") int id, Model model) {

        Hairdresser hairdresser = new Hairdresser();
        hairdresser.setHairSalon(hairSalonService.get(id));
        model.addAttribute("hairdresserDto", hairdresser);
        return "hairdresser-add-page";
    }

    @PostMapping("/processHairdresserAdd")
    public String processHairdresserAdd(@ModelAttribute("hairdresserDto") HairdresserDto hairdresserDto) {
        int id = hairdresserDto.getHairSalon().getId();
        hairdresserService.save(hairdresserDto);
        return "redirect:/owner/salonHairdressers?id=" + id;
    }

    @RequestMapping("/deleteHairdresser")
    @Transactional
    public String deleteHairdresser(@RequestParam(value = "id") int id){
        Hairdresser hairdresser = hairdresserService.getOne(id);
        Hibernate.initialize(hairdresser.getHairSalon());
        id = hairdresser.getHairSalon().getId();
        hairdresserService.delete(hairdresser);
        return "redirect:/owner/salonHairdressers?id=" + id;
    }
}
