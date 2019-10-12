package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dto.HairdresserDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;

import java.util.List;

public interface HairdresserService {

    List<Hairdresser> findAllByHairSalon(HairSalon hairSalon);

    void save(HairdresserDto hairdresserDto);
}
