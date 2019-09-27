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
@Table(name = "hair_salon")
public class HairSalon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id")
    private int id;

    @Column(name = "salon_name")
    private String salonName;

    @Column(name = "salon_address")
    private String salonAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "HAIR_SALON_TO_OWNER_FK"))
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "hairSalon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "hairSalon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SalonRating> salonRatings;

    @OneToMany(mappedBy = "hairSalon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Hairdresser> hairdressers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "salons_services",
            joinColumns = @JoinColumn(name = "salon_id", foreignKey = @ForeignKey(name = "SALON_TO_SALONS_SERVICES_FK")),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonIgnore
    private List<HairService> hairServices;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "salon_hours",
            joinColumns = @JoinColumn(name = "salon_id", foreignKey = @ForeignKey(name = "HAIR_SALON_TO_HOURS_FK")),
            inverseJoinColumns = @JoinColumn(name = "hours_id"))
    @JsonIgnore
    private List<HairSalonOpeningHours> hairSalonOpeningHoursList;

    public HairSalon() {
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

    public void setPhoneNumber(String phoneNumer) {
        this.phoneNumber = phoneNumer;
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

    @Override
    public String toString() {
        return "HairSalon{" +
                "id=" + id +
                ", salonName='" + salonName + '\'' +
                ", salonAddress='" + salonAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
