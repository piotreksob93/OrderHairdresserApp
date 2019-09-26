package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;

import java.util.ArrayList;
import java.util.List;

public class HairSalonOpeningHoursWrapper {

    private List<HairSalonOpeningHours> days;

    public HairSalonOpeningHoursWrapper() {
        days = new ArrayList<>();
    }

    public void addDay(HairSalonOpeningHours day) {
        days.add(day);
    }

    public List<HairSalonOpeningHours> getDays() {
        return days;
    }

    public void setDays(List<HairSalonOpeningHours> days) {
        this.days = days;
    }
}
