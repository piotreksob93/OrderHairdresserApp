package com.piotrek.apps.orderHaircutApp.dto;

public class HairSalonOpeningHoursDto {

    private int id;

    private String dayName;

    private String salonOpenHour;

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
