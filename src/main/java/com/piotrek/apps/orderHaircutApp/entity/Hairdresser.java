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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "hairdresser")
public class Hairdresser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hairdresser_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "hairdresser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HairdresserRating> hairdresserRatings;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "salon_id", foreignKey = @ForeignKey(name = "HAIR_SALON_TO_HAIRDRESSER_FK"))
    @JsonIgnore
    private HairSalon hairSalon;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hairdresser_service",
            joinColumns = @JoinColumn(name = "hairdresser_id", foreignKey = @ForeignKey(name = "HAIRDRESSER_TO_HAIRDRESSERS_SERVICES_FK")),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonIgnore
    private List<HairService> hairServices;

    @OneToMany(mappedBy = "hairdresser", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<HairServiceOnReservation> hairServiceOnReservations;

    @OneToMany(mappedBy = "hairdresser", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<HairdresserWorkHours> hairdresserWorkHours;

    public Hairdresser() {
    }

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

    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hairdresserRatings=" + hairdresserRatings +
                ", hairSalon=" + hairSalon +
                ", hairServices=" + hairServices +
                ", hairServiceOnReservations=" + hairServiceOnReservations +
                '}';
    }
}
