package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.entity.Reservation;
import com.piotrek.apps.orderHaircutApp.entity.SalonRating;
import com.piotrek.apps.orderHaircutApp.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class HairSalonDto {

    private int id;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String salonName;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String salonAddress;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String city;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    @Pattern(regexp = "^\\d{3}[ ]?\\d{3}[ ]?\\d{3}", message = "Podaj prawidłowy numer telefonu")
    private String phoneNumber;

    @NotNull(message = "pole wymagane")
    private String salonOpenHour;

    @NotNull(message = "pole wymagane")
    private String salonCloseHour;

    private User user;

    private List<Reservation> reservations;

    private List<SalonRating> salonRatings;

    private List<Hairdresser> hairdressers;

    private List<HairService> hairServices;

    private List<HairSalonOpeningHoursDto> hairSalonOpeningHoursDtoList;

    public HairSalonDto() {
        hairSalonOpeningHoursDtoList = new ArrayList<>();
    }

    public void addDay(HairSalonOpeningHoursDto day) {
        hairSalonOpeningHoursDtoList.add(day);
    }

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

    public List<HairSalonOpeningHoursDto> getHairSalonOpeningHoursDtoList() {
        return hairSalonOpeningHoursDtoList;
    }

    public void setHairSalonOpeningHoursDtoList(List<HairSalonOpeningHoursDto> hairSalonOpeningHoursDtoList) {
        this.hairSalonOpeningHoursDtoList = hairSalonOpeningHoursDtoList;
    }
}
