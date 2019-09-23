package com.piotrek.apps.orderHaircutApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piotrek.apps.orderHaircutApp.enums.ReservationStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @Column(name = "reservation_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    //kto złożył rezerwację
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_TO_RESERVATION_RATING_FK"))
    @JsonIgnore
    private User user;
    //w jakim salonie
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "salon_id", foreignKey = @ForeignKey(name = "RESERVATION_TO_SALON_RATING_FK"))
    @JsonIgnore
    private HairSalon hairSalon;
    //u jakiego fryzjera

    @OneToMany(mappedBy = "reservation" ,fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<HairServiceOnReservation> hairServiceOnReservations;

    public Reservation() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HairServiceOnReservation> getHairServiceOnReservations() {
        return hairServiceOnReservations;
    }

    public void setHairServiceOnReservations(List<HairServiceOnReservation> hairServiceOnReservations) {
        this.hairServiceOnReservations = hairServiceOnReservations;
    }

    public HairSalon getHairSalon() {
        return hairSalon;
    }

    public void setHairSalon(HairSalon hairSalon) {
        this.hairSalon = hairSalon;
    }

//    public List<HairService> getHairServices() {
//        return hairServices;
//    }
//
//    public void setHairServices(List<HairService> hairServices) {
//        this.hairServices = hairServices;
//    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
