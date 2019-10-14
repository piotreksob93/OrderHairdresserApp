package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairdresserDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.HairdresserService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
    @Transactional
    public String showHairdresserAddPage(@RequestParam(value = "id") int id, Model model) {

        HairdresserDto hairdresserDto = new HairdresserDto();
        HairSalon hairSalon = hairSalonService.get(id);
        hairdresserDto.setHairSalon(hairSalon);
        hairdresserDto.getHairSalon().setSalonName(hairSalon.getSalonName());
        model.addAttribute("hairdresserDto", hairdresserDto);
        return "hairdresser-add-page";
    }

    @PostMapping("/processHairdresserAdd")
    public String processHairdresserAdd(@Valid  @ModelAttribute("hairdresserDto") HairdresserDto hairdresserDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "hairdresser-add-page";
        }
        int id = hairdresserDto.getHairSalon().getId();
        hairdresserDto.setHairSalon(hairSalonService.get(id));
        hairdresserService.save(hairdresserDto);
        return "redirect:/hairsalon/salonHairdressers?id=" + id;
    }

    @RequestMapping("/deleteHairdresser")
    @Transactional
    public String deleteHairdresser(@RequestParam(value = "id") int id) {
        Hairdresser hairdresser = hairdresserService.getOne(id);
        Hibernate.initialize(hairdresser.getHairSalon());
        id = hairdresser.getHairSalon().getId();
        hairdresserService.delete(hairdresser);
        return "redirect:/hairsalon/salonHairdressers?id=" + id;
    }

    @RequestMapping("/editHairdresser")
    @Transactional
    public String editHairdresser(@RequestParam(value = "id") int id, Model model) {
        Hairdresser hairdresser = hairdresserService.getOne(id);
        Hibernate.initialize(hairdresser.getHairSalon());
        model.addAttribute("hairdresserDto", hairdresser);
        return "hairdresser-add-page";
    }
}
