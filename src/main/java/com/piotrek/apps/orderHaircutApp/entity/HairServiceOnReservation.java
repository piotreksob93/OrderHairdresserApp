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
import javax.persistence.Table;

@Entity
@Table(name = "hair_service_on_reservation")
public class HairServiceOnReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "hairdresser_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_ON_RESERVATION_TO_HAIRDRESSER_FK"))
    @JsonIgnore
    private Hairdresser hairdresser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "hairservice_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_ON_RESERVATION_TO_HAIR_SERVICE_FK"))
    @JsonIgnore
    private HairService hairService;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "reservation_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_ON_RESERVATION_TO_RESERVATION_FK"))
    @JsonIgnore
    private Reservation reservation;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
