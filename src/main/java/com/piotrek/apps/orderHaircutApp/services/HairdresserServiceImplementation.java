package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dao.HairdresserRepo;
import com.piotrek.apps.orderHaircutApp.dto.HairdresserDto;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HairdresserServiceImplementation implements HairdresserService {

    private final HairdresserRepo hairdresserRepo;

    public HairdresserServiceImplementation(HairdresserRepo hairdresserRepo) {
        this.hairdresserRepo = hairdresserRepo;
    }

    @Override
    public List<Hairdresser> findAllByHairSalon(HairSalon hairSalon) {
        return hairdresserRepo.findAllByHairSalon(hairSalon);
    }

    @Override
    @Transactional
    public void save(HairdresserDto hairdresserDto) {
        Hairdresser hairdresser = new Hairdresser();
        hairdresser.setFirstName(hairdresserDto.getFirstName());
        hairdresser.setLastName(hairdresserDto.getLastName());
        hairdresser.setHairSalon(hairdresserDto.getHairSalon());
        hairdresserRepo.save(hairdresser);
    }
}
