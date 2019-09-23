package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.SalonRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRatingRepo extends JpaRepository<SalonRating, Integer> {
}
