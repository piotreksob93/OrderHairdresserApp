package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.HairSalon;
import com.piotrek.apps.orderHaircutApp.entity.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HairdresserRepo extends JpaRepository<Hairdresser, Integer> {

    List<Hairdresser> findAllByHairSalon(HairSalon hairSalon);
}
