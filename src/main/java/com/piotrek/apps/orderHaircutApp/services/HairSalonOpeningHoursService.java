package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;

public interface HairSalonOpeningHoursService {

    HairSalonOpeningHours get(int id);

    void delete(HairSalonOpeningHours hairSalonOpeningHours);
}
