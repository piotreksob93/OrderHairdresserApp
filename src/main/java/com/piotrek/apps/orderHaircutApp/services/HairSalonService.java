package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dto.HairSalonDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface HairSalonService {

    HairSalon get(int id);

    void save(HairSalonDto hairSalonDto, Authentication authentication);

    List<HairSalon> findAllByUser(User user);

    void delete(HairSalon hairSalon);

    void deleteById(int id);
}
