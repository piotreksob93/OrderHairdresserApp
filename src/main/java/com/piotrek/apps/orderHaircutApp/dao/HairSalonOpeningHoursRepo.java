package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.HairSalonOpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HairSalonOpeningHoursRepo extends JpaRepository<HairSalonOpeningHours,Integer> {
}
