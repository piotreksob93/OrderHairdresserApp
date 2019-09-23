package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.HairdresserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdresserRatingRepo extends JpaRepository<HairdresserRating, Integer> {
}
