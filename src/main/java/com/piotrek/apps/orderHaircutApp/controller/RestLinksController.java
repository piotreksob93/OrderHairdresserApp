package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dao.UserRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairSalonRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairServiceRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairdresserRatingRepo;
import com.piotrek.apps.orderHaircutApp.dao.HairdresserRepo;
import com.piotrek.apps.orderHaircutApp.dao.ReservationRepo;
import com.piotrek.apps.orderHaircutApp.dao.SalonRatingRepo;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import com.piotrek.apps.orderHaircutApp.entity.HairdresserRating;
import com.piotrek.apps.orderHaircutApp.entity.Reservation;
import com.piotrek.apps.orderHaircutApp.entity.SalonRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestLinksController {

    private final HairServiceRepo hairServiceRepo;

    private final ReservationRepo reservationRepo;

    private final UserRepo userRepo;

    private final SalonRatingRepo salonRatingRepo;

    private final HairSalonRepo hairSalonRepo;

    private final HairdresserRatingRepo hairdresserRatingRepo;

    private final HairdresserRepo hairdresserRepo;

    @Autowired
    public RestLinksController(HairServiceRepo hairServiceRepo, ReservationRepo reservationRepo,
                               UserRepo userRepo, SalonRatingRepo salonRatingRepo, HairSalonRepo hairSalonRepo,
                               HairdresserRatingRepo hairdresserRatingRepo, HairdresserRepo hairdresserRepo) {
        this.hairServiceRepo = hairServiceRepo;
        this.reservationRepo = reservationRepo;
        this.userRepo = userRepo;
        this.salonRatingRepo = salonRatingRepo;
        this.hairSalonRepo = hairSalonRepo;
        this.hairdresserRatingRepo = hairdresserRatingRepo;
        this.hairdresserRepo = hairdresserRepo;
    }

    @GetMapping("/services")
    public List<HairService> getServices() {
        return hairServiceRepo.findAll();
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
//        for (Reservation res : reservations) {
//            Hibernate.initialize(res.getClient());
//            Hibernate.initialize(res.getHairSalon());
//        }
        return reservations;
    }

    @GetMapping("/users")
    public List<User> getClients() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @GetMapping("/salons")
    public List<HairSalon> getSalons() {
        return hairSalonRepo.findAll();
    }

    @GetMapping("/hairdressers")
    public List<Hairdresser> getHairdressers() {
        return hairdresserRepo.findAll();
    }

    @Transactional
    @GetMapping("/hairdresserRatings")
    public List<HairdresserRating> getHairdresserRatings() {
        List<HairdresserRating> hairdresserRatings = hairdresserRatingRepo.findAll();
//        for (HairdresserRating hairdresserRating : hairdresserRatings) {
//            Hibernate.initialize(hairdresserRating.getClient());
//            Hibernate.initialize(hairdresserRating.getHairdresser());
//        }
        return hairdresserRatings;
    }


    @GetMapping("/salonRatings")
    public List<SalonRating> getSalonRatings() {
        return salonRatingRepo.findAll();
    }

}