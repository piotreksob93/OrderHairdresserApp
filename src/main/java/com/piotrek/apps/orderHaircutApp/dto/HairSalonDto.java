package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.entity.Reservation;
import com.piotrek.apps.orderHaircutApp.entity.SalonRating;
import com.piotrek.apps.orderHaircutApp.entity.User;

import java.util.List;

public class HairSalonDto {

    private int id;

    private String salonName;

    private String salonAddress;

    private String city;

    private String phoneNumber;

    private User user;

    private List<Reservation> reservations;

    private List<SalonRating> salonRatings;

    private List<Hairdresser> hairdressers;

    private List<HairService> hairServices;

    private List<HairSalonOpeningHours> hairSalonOpeningHoursList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<SalonRating> getSalonRatings() {
        return salonRatings;
    }

    public void setSalonRatings(List<SalonRating> salonRatings) {
        this.salonRatings = salonRatings;
    }

    public List<Hairdresser> getHairdressers() {
        return hairdressers;
    }

    public void setHairdressers(List<Hairdresser> hairdressers) {
        this.hairdressers = hairdressers;
    }

    public List<HairService> getHairServices() {
        return hairServices;
    }

    public void setHairServices(List<HairService> hairServices) {
        this.hairServices = hairServices;
    }

    public List<HairSalonOpeningHours> getHairSalonOpeningHoursList() {
        return hairSalonOpeningHoursList;
    }

    public void setHairSalonOpeningHoursList(List<HairSalonOpeningHours> hairSalonOpeningHoursList) {
        this.hairSalonOpeningHoursList = hairSalonOpeningHoursList;
    }
}
