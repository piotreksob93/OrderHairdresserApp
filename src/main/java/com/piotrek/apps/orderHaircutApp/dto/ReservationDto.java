package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairServiceOnReservation;
import com.piotrek.apps.orderHaircutApp.enums.ReservationStatus;

import java.util.Date;
import java.util.List;

public class ReservationDto {

    private int id;

    private Date reservationDate;

    private ReservationStatus reservationStatus;

    private User client;

    private HairSalon hairSalon;

    private List<HairServiceOnReservation> hairServiceOnReservations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public HairSalon getHairSalon() {
        return hairSalon;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }

    public List<HairServiceOnReservation> getHairServiceOnReservations() {
        return hairServiceOnReservations;
    }

    public void setHairServiceOnReservations(List<HairServiceOnReservation> hairServiceOnReservations) {
        this.hairServiceOnReservations = hairServiceOnReservations;
    }
}
