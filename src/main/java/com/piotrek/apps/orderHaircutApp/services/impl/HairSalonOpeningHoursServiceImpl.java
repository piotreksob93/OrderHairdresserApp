package com.piotrek.apps.orderHaircutApp.services.impl;

import com.piotrek.apps.orderHaircutApp.dao.HairSalonOpeningHoursRepo;
import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.services.HairSalonOpeningHoursService;
import org.springframework.stereotype.Service;

@Service
public class HairSalonOpeningHoursServiceImpl implements HairSalonOpeningHoursService {

    private final HairSalonOpeningHoursRepo hairSalonOpeningHoursRepo;

    public HairSalonOpeningHoursServiceImpl(HairSalonOpeningHoursRepo hairSalonOpeningHoursRepo) {
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
