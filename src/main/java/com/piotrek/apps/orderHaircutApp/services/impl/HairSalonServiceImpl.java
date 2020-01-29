package com.piotrek.apps.orderHaircutApp.services.impl;

import com.piotrek.apps.orderHaircutApp.dao.HairSalonRepo;
import com.piotrek.apps.orderHaircutApp.dao.UserRepo;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonOpeningHoursDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.services.HairSalonService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HairSalonServiceImpl implements HairSalonService {

    private final HairSalonRepo hairSalonRepo;

    private final UserRepo userRepo;

    public HairSalonServiceImpl(HairSalonRepo hairSalonRepo, UserRepo userRepo) {
        this.hairSalonRepo = hairSalonRepo;
        this.userRepo = userRepo;
    }

    @Override
    public HairSalon get(int id) {
        return hairSalonRepo.getOne(id);
    }

    @Override
    public void save(HairSalonDto hairSalonDto, Authentication authentication) {
        HairSalon hairSalon = new HairSalon();
        hairSalon.setId(hairSalonDto.getId());
        hairSalon.setSalonName(hairSalonDto.getSalonName());
        hairSalon.setSalonAddress(hairSalonDto.getSalonAddress());
        hairSalon.setPhoneNumber(hairSalonDto.getPhoneNumber());
        hairSalon.setCity(hairSalonDto.getCity());
        List<HairSalonOpeningHours> hairSalonOpeningHoursList = new ArrayList<>();

        for (HairSalonOpeningHoursDto day : hairSalonDto.getHairSalonOpeningHoursDtoList()) {
            HairSalonOpeningHours hours = new HairSalonOpeningHours();
            hours.setId(day.getId());
            hours.setDayName(day.getDayName());
            hours.setSalonOpenHour(day.getSalonOpenHour());
            hours.setSalonCloseHour(day.getSalonCloseHour());
            hairSalonOpeningHoursList.add(hours);
        }

        hairSalon.setHairSalonOpeningHoursList(hairSalonOpeningHoursList);
        hairSalon.setUser(userRepo.findByUserName(authentication.getName()));

        hairSalonRepo.save(hairSalon);
    }

    @Override
    public List<HairSalon> findAllByUser(User user) {
        return hairSalonRepo.findAllByUser(user);
    }

    @Override
    public void delete(HairSalon hairSalon) {
        hairSalonRepo.delete(hairSalon);
    }

    @Override
    public void deleteById(int id) {
        hairSalonRepo.deleteById(id);
    }

}
