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
import javax.persistence.ManyToOne;

@Entity
public class HairdresserWorkHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hours_id")
    private int id;

    @Column(name = "dayName")
    private String dayName;

    @Column(name = "work_start_hour", columnDefinition = "time")
    private String workStartHour;

    @Column(name = "work_end_hour", columnDefinition = "time")
    private String workEndHour;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "hairdresser_id", foreignKey = @ForeignKey(name = "afoawbfowabfwbaf_SERVICE_FK"))
    @JsonIgnore
    private Hairdresser hairdresser;

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

    public String getWorkStartHour() {
        return workStartHour;
    }

    public void setWorkStartHour(String workStartHour) {
        this.workStartHour = workStartHour;
    }

    public String getWorkEndHour() {
        return workEndHour;
    }

    public void setWorkEndHour(String workEndHour) {
        this.workEndHour = workEndHour;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    @Override
    public String toString() {
        return "HairdresserWorkHours{" +
                "id=" + id +
                ", dayName='" + dayName + '\'' +
                ", workStartHour='" + workStartHour + '\'' +
                ", workEndHour='" + workEndHour + '\'' +
                ", hairdresser=" + hairdresser +
                '}';
    }
}
