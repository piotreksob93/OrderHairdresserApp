package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dao.HairSalonRepo;
import com.piotrek.apps.orderHaircutApp.dao.UserRepo;
import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HairSalonServiceImplementation implements HairSalonService {

    private final HairSalonRepo hairSalonRepo;

    private final UserRepo userRepo;

    public HairSalonServiceImplementation(HairSalonRepo hairSalonRepo, UserRepo userRepo) {
        this.hairSalonRepo = hairSalonRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void save(HairSalonDto hairSalonDto, Authentication authentication) {
        HairSalon hairSalon = new HairSalon();
        hairSalon.setSalonName(hairSalonDto.getSalonName());
        hairSalon.setSalonAddress(hairSalonDto.getSalonAddress());
        hairSalon.setPhoneNumber(hairSalonDto.getPhoneNumber());
        hairSalon.setCity(hairSalonDto.getCity());
        hairSalon.setHairSalonOpeningHoursList(hairSalonDto.getHairSalonOpeningHoursList());
        User user = userRepo.findByUserName(authentication.getName());
        hairSalon.setUser(user);

        hairSalonRepo.save(hairSalon);
    }

    @Override
    public List<HairSalon> findAllByUser(User user) {
        return hairSalonRepo.findAllByUser(user);
    }


}
