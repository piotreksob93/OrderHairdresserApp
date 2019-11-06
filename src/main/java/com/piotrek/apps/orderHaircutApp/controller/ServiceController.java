package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dto.HairServiceDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import com.piotrek.apps.orderHaircutApp.services.HairServiceService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private final HairServiceService hairServiceService;

    private final HairSalonService hairSalonService;

    public ServiceController(HairServiceService hairServiceService, HairSalonService hairSalonService) {
        this.hairServiceService = hairServiceService;
        this.hairSalonService = hairSalonService;
    }

    @RequestMapping("/delete")
    @Transactional
    public String deleteService(@RequestParam ("id") int id){
        HairService hairService =  hairServiceService.getOne(id);
        int salonId = hairService.getHairSalon().get(0).getId();
        System.out.println(salonId);
        hairServiceService.delete(hairService);
        return "redirect:/hairsalon/salonServices?id=" + salonId;
    }


    @RequestMapping("/addService")
    @Transactional
    public String showServiceAddPage(@RequestParam ("id") int id, Model model){
        HairServiceDto hairServiceDto = new HairServiceDto();
        HairSalon hairSalon = hairSalonService.get(id);
        List<HairSalon> hairSalons = new ArrayList<>();
        hairSalons.add(hairSalon);
        hairServiceDto.setHairSalon(hairSalons);
        model.addAttribute("hairServiceDto",hairServiceDto);
        return "hair-service-add-page";
    }

    @PostMapping("/processServiceAdd")
    @Transactional
    public String processServiceAdd(@Valid @ModelAttribute ("hairServiceDto") HairServiceDto hairServiceDto,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hair-service-add-page";
        }

        int id = hairServiceDto.getHairSalon().get(0).getId();
        //hairServiceDto.setHairSalon(null);
        hairServiceService.save(hairServiceDto);
        return "redirect:/hairsalon/salonServices?id=" + id;
//        return "redirect:/hairsalon/salonServices?id=" + 1;
    }

    @RequestMapping("/edit")
    @Transactional
    public String showServiceEditPage(@RequestParam ("id") int id, Model model){
        HairService hairService = hairServiceService.getOne(id);
        HairServiceDto hairServiceDto = new HairServiceDto();
        hairServiceDto.setId(hairService.getId());
        hairServiceDto.setServiceName(hairService.getServiceName());
        hairServiceDto.setServicePrice(hairService.getServicePrice());
        hairServiceDto.setExecutionTime(hairService.getExecutionTime());

//        Hibernate.initialize(hairService.getHairServiceOnReservations());
//        hairServiceDto.setHairServiceOnReservations(hairService.getHairServiceOnReservations());
//
//        Hibernate.initialize(hairService.getHairdressers());
//        hairServiceDto.setHairdressers(hairService.getHairdressers());
//
        Hibernate.initialize(hairService.getHairSalon());
        hairServiceDto.setHairSalon(hairService.getHairSalon());

        model.addAttribute("hairServiceDto",hairServiceDto);
        return "hair-service-add-page";
    }

}
