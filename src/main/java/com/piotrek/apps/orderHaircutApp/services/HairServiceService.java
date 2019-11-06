package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dto.HairServiceDto;
import com.piotrek.apps.orderHaircutApp.entity.HairService;

public interface HairServiceService {

    void delete(HairService hairService);

    HairService getOne(int id);

    void save(HairServiceDto hairServiceDto);
}
