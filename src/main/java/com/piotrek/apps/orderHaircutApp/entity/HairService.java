package com.piotrek.apps.orderHaircutApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hair_service")
public class HairService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    int id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "execution_time")
    private int executionTime;
    @Column(name = "price")
    private int servicePrice;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "salons_services",
            joinColumns = @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_TO_SALONS_SERVICES_FK")),
            inverseJoinColumns = @JoinColumn(name = "salon_id"))
    @JsonIgnore
    private List<HairSalon> hairSalon;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "hairdresser_service",
            joinColumns = @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_TO_HAIRDRESSERS_SERVICES_FK")),
            inverseJoinColumns = @JoinColumn(name = "hairdresser_id"))
    @JsonIgnore
    private List<Hairdresser> hairdressers;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "reservations_services",
//            joinColumns = @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "HAIR_SERVICE_TO_RESERVATIONS_SERVICES_FK")),
//            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
//    @JsonIgnore
//    private List<Reservation> reservations;

    @OneToMany(mappedBy = "hairService" ,fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<HairServiceOnReservation> hairServiceOnReservations;

    public HairService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public List<HairSalon> getHairSalon() {
        return hairSalon;
    }

    public void setHairSalon(List<HairSalon> hairSalon) {
        this.hairSalon = hairSalon;
    }

    public List<Hairdresser> getHairdressers() {
        return hairdressers;
    }

    public void setHairdressers(List<Hairdresser> hairdressers) {
        this.hairdressers = hairdressers;
    }

//    public List<Reservation> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(List<Reservation> reservations) {
//        this.reservations = reservations;
//    }


    public List<HairServiceOnReservation> getHairServiceOnReservations() {
        return hairServiceOnReservations;
    }

    public void setHairServiceOnReservations(List<HairServiceOnReservation> hairServiceOnReservations) {
        this.hairServiceOnReservations = hairServiceOnReservations;
    }

    @Override
    public String toString() {
        return "HairService{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", executionTime=" + executionTime +
                ", servicePrice=" + servicePrice +
                ", hairSalon=" + hairSalon +
                ", hairdressers=" + hairdressers +
                ", hairServiceOnReservations=" + hairServiceOnReservations +
                '}';
    }
}
