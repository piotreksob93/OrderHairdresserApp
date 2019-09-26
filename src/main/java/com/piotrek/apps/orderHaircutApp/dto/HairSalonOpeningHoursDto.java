package com.piotrek.apps.orderHaircutApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


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
