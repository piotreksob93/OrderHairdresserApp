package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.enums.Days;

import javax.validation.constraints.NotNull;

public class HairSalonOpeningHoursDto {

    private int id;

    private String dayName;

    private Enum<Days> dayNameForm;

    @NotNull(message = "pole wymagane")
    private String salonOpenHour;

    @NotNull(message = "pole wymagane")
    private String salonCloseHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Enum<Days> getDayNameForm() {
        return dayNameForm;
    }

    public void setDayNameForm(Enum<Days> dayNameForm) {
        this.dayNameForm = dayNameForm;
    }

    public String getSalonOpenHour() {
        return salonOpenHour;
    }

    public void setSalonOpenHour(String salonOpenHour) {
        this.salonOpenHour = salonOpenHour;
    }

    public String getSalonCloseHour() {
        return salonCloseHour;
    }

    public void setSalonCloseHour(String salonCloseHour) {
        this.salonCloseHour = salonCloseHour;
    }

}
