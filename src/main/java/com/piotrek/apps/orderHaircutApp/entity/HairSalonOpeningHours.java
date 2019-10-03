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
import java.util.List;

@Entity
@Table(name = "hair_salon_opening_hours")
public class HairSalonOpeningHours implements Comparable<HairSalonOpeningHours> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hours_id")
    private int id;

    @Column(name = "dayName")
    private String dayName;

    @Column(name = "salon_open_hour", columnDefinition = "time")
    private String salonOpenHour;

    @Column(name = "salon_close_hour", columnDefinition = "time")
    private String salonCloseHour;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
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
                ", dayName='" + dayName + '\'' +
                ", salonOpenHour='" + salonOpenHour + '\'' +
                ", salonCloseHour='" + salonCloseHour + '\'' +
                '}';
    }

    @Override
    public int compareTo(HairSalonOpeningHours o) {
        return this.id-o.id;
    }
}
