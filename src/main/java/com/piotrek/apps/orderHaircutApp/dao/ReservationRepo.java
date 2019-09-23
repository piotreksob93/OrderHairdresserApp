package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
}
