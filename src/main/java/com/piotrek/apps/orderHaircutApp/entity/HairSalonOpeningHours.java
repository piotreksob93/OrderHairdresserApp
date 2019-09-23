package com.piotrek.apps.orderHaircutApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "hair_salon_opening_hours")
public class HairSalonOpeningHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hours_id")
    private int id;

    @Column(name = "day")
    private int day;

    @Column(name = "salon_open_hour",columnDefinition = "time")
    private Time salonOpenHour;

    @Column(name = "salon_close_hour",columnDefinition = "time")
    private Time salonCloseHour;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "salon_hours",
            joinColumns = @JoinColumn(name = "hours_id", foreignKey = @ForeignKey(name = "HOURS_TO_HAIR_SALON_FK")),
            inverseJoinColumns = @JoinColumn(name = "salon_id"))
    @JsonIgnore
    private List<HairSalon> hairSalonList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getSalonOpenHour() {
        return salonOpenHour;
    }

    public void setSalonOpenHour(Time salonOpenHour) {
        this.salonOpenHour = salonOpenHour;
    }

    public Time getSalonCloseHour() {
        return salonCloseHour;
    }

    public void setSalonCloseHour(Time salonCloseHour) {
        this.salonCloseHour = salonCloseHour;
    }

    public List<HairSalon> getHairSalonList() {
        return hairSalonList;
    }

    public void setHairSalonList(List<HairSalon> hairSalonList) {
        this.hairSalonList = hairSalonList;
    }

    @Override
    public String toString() {
        return "HairSalonOpeningHours{" +
                "id=" + id +
                ", day=" + day +
                ", salonOpenHour=" + salonOpenHour +
                ", salonCloseHour=" + salonCloseHour +
                '}';
    }
}
