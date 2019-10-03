package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dao.HairSalonOpeningHoursRepo;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import org.springframework.stereotype.Service;

@Service
public class HairSalonOpeningHoursServiceImplementation implements HairSalonOpeningHoursService {

    private final HairSalonOpeningHoursRepo hairSalonOpeningHoursRepo;

    public HairSalonOpeningHoursServiceImplementation(HairSalonOpeningHoursRepo hairSalonOpeningHoursRepo) {
        this.hairSalonOpeningHoursRepo = hairSalonOpeningHoursRepo;
    }

    @Override
    public HairSalonOpeningHours get(int id) {
        return hairSalonOpeningHoursRepo.getOne(id);
    }

    @Override
    public void delete(HairSalonOpeningHours hairSalonOpeningHours) {
        hairSalonOpeningHoursRepo.delete(hairSalonOpeningHours);
    }
}
