package com.piotrek.apps.orderHaircutApp.dao;

import com.piotrek.apps.orderHaircutApp.entity.HairService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairServiceRepo extends JpaRepository<HairService, Integer> {

    HairService findByIdEquals(int id);
}
