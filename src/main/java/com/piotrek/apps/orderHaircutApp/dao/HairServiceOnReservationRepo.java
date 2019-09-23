package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.HairServiceOnReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HairServiceOnReservationRepo extends JpaRepository<HairServiceOnReservation, Integer> {

    List<HairServiceOnReservation> findByReservationId(int id);

}
