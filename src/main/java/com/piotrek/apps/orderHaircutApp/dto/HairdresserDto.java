package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.entity.HairServiceOnReservation;
import com.piotrek.apps.orderHaircutApp.entity.HairdresserRating;
import com.piotrek.apps.orderHaircutApp.entity.HairdresserWorkHours;

import java.util.List;

public class HairdresserDto {

    private int id;

    private String firstName;

    private String lastName;

    private List<HairdresserRating> hairdresserRatings;

    private float averageRating;

    private HairSalon hairSalon;

    private List<HairService> hairServices;

    private List<HairServiceOnReservation> hairServiceOnReservations;

    private List<HairdresserWorkHours> hairdresserWorkHours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<HairdresserRating> getHairdresserRatings() {
        return hairdresserRatings;
    }

    public void setHairdresserRatings(List<HairdresserRating> hairdresserRatings) {
        this.hairdresserRatings = hairdresserRatings;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public HairSalon getHairSalon() {
        return hairSalon;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }

    public List<HairService> getHairServices() {
        return hairServices;
    }

    public void setHairServices(List<HairService> hairServices) {
        this.hairServices = hairServices;
    }

    public List<HairServiceOnReservation> getHairServiceOnReservations() {
        return hairServiceOnReservations;
    }

    public void setHairServiceOnReservations(List<HairServiceOnReservation> hairServiceOnReservations) {
        this.hairServiceOnReservations = hairServiceOnReservations;
    }

    public List<HairdresserWorkHours> getHairdresserWorkHours() {
        return hairdresserWorkHours;
    }

    public void setHairdresserWorkHours(List<HairdresserWorkHours> hairdresserWorkHours) {
        this.hairdresserWorkHours = hairdresserWorkHours;
    }
}
