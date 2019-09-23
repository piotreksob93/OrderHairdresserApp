package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dao.UserRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairSalonRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairServiceOnReservationRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairServiceRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairdresserRatingRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairdresserRepo;
import com.piotrek.apps.orderHaircutApp.dao.ReservationRepo;
import com.piotrek.apps.orderHaircutApp.dao.SalonRatingRepo;
import com.piotrek.apps.orderHaircutApp.dto.ReservationDto;
import com.piotrek.apps.orderHaircutApp.entity.Reservation;
import com.piotrek.apps.orderHaircutApp.entity.Role;
import com.piotrek.apps.orderHaircutApp.entity.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class TestController {

    private final HairServiceRepo hairServiceRepo;

    private final ReservationRepo reservationRepo;

    private final UserRepo userRepo;

    private final SalonRatingRepo salonRatingRepo;

    private final HairSalonRepo hairSalonRepo;

    private final HairdresserRatingRepo hairdresserRatingRepo;

    private final HairdresserRepo hairdresserRepo;

    private final HairServiceOnReservationRepo hairServiceOnReservationRepo;

    @Autowired
    public TestController(HairServiceRepo hairServiceRepo, ReservationRepo reservationRepo, UserRepo userRepo,
                          SalonRatingRepo salonRatingRepo, HairSalonRepo hairSalonRepo,
                          HairdresserRatingRepo hairdresserRatingRepo, HairdresserRepo hairdresserRepo,
                          HairServiceOnReservationRepo hairServiceOnReservationRepo) {
        this.hairServiceRepo = hairServiceRepo;
        this.reservationRepo = reservationRepo;
        this.userRepo = userRepo;
        this.salonRatingRepo = salonRatingRepo;
        this.hairSalonRepo = hairSalonRepo;
        this.hairdresserRatingRepo = hairdresserRatingRepo;
        this.hairdresserRepo = hairdresserRepo;
        this.hairServiceOnReservationRepo = hairServiceOnReservationRepo;
    }

    @Transactional
    @GetMapping("/testowy")
    public String metodaDoTestow(Model model) {
        List<ReservationDto> reservations = new ArrayList<>();
        for (Reservation reservation : reservationRepo.findAll()) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(reservation.getId());
            reservationDto.setReservationDate(reservation.getReservationDate());
            reservationDto.setReservationStatus(reservation.getReservationStatus());
            Hibernate.initialize(reservation.getUser());
            reservationDto.setClient(reservation.getUser());
            Hibernate.initialize(reservation.getHairSalon());
            reservationDto.setHairSalon(reservation.getHairSalon());
            reservationDto.setHairServiceOnReservations(hairServiceOnReservationRepo.findByReservationId(reservation.getId()));
            reservations.add(reservationDto);
        }
        model.addAttribute("rezerwacje", reservations);
        return "test";
    }

    @Transactional
    @GetMapping("/rest")
    public String showRestLinksPage() {
        return "rest";
    }

    @Transactional
    @GetMapping("/testowy2")
    public String metodaDoTestow2(Model model) {
        User user = userRepo.getOne(6);
        Hibernate.initialize(user.getRole());
        model.addAttribute("user", user);
        return "test2";
    }


}
