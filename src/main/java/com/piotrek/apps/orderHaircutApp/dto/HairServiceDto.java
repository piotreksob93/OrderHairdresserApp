package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairServiceOnReservation;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import org.hibernate.annotations.Type;

import javax.validation.constraints.*;
import java.util.List;

public class HairServiceDto {

    private int id;

    @Size(min = 1, message = "Pole wymagane")
    @NotNull(message = "Pole wymagane")
    private String serviceName;

    @Min(1)
    @Max(999)
    @NotNull(message = "Pole wymagane")
    @Digits(integer = 3, fraction = 0, message = "Wprowadz liczbę")
    @Type(type = "int")
    private int executionTime;

    @Min(1)
    @Max(999)
    @NotNull(message = "Pole wymagane")
    @Digits(integer = 3, fraction = 0, message = "Wprowadz liczbę")
    private int servicePrice;

    private List<HairSalon> hairSalon;

    private List<Hairdresser> hairdressers;

    private List<HairServiceOnReservation> hairServiceOnReservations;

    public HairServiceDto() {
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

    public List<HairServiceOnReservation> getHairServiceOnReservations() {
        return hairServiceOnReservations;
    }

    public void setHairServiceOnReservations(List<HairServiceOnReservation> hairServiceOnReservations) {
        this.hairServiceOnReservations = hairServiceOnReservations;
    }
}
