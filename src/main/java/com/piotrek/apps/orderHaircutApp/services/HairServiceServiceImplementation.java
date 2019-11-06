package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dao.HairServiceRepo;
import com.piotrek.apps.orderHaircutApp.dto.HairServiceDto;
import com.piotrek.apps.orderHaircutApp.entity.HairService;
import org.springframework.stereotype.Service;

@Service
public class HairServiceServiceImplementation implements HairServiceService {

    private final HairServiceRepo hairServiceRepo;

    public HairServiceServiceImplementation(HairServiceRepo hairServiceRepo) {
        this.hairServiceRepo = hairServiceRepo;
    }

    @Override
    public void delete(HairService hairService) {
        hairServiceRepo.delete(hairService);
    }

    @Override
    public HairService getOne(int id) {
        return hairServiceRepo.getOne(id);
    }

    @Override
    public void save(HairServiceDto hairServiceDto) {
        HairService hairService;
        if (hairServiceDto.getId()!=0){
            hairService = hairServiceRepo.getOne(hairServiceDto.getId());
        }else{
            hairService = new HairService();
            hairService.setHairSalon(hairServiceDto.getHairSalon());
        }
        hairService.setId(hairServiceDto.getId());
        hairService.setExecutionTime(hairServiceDto.getExecutionTime());
        hairService.setServiceName(hairServiceDto.getServiceName());
        hairService.setServicePrice(hairServiceDto.getServicePrice());
        if (hairServiceDto.getHairdressers()!=null)
        hairService.setHairdressers(hairServiceDto.getHairdressers());
//        if (hairServiceDto.getHairSalon()!=null)
//        hairService.setHairSalon(hairServiceDto.getHairSalon());
        if (hairServiceDto.getHairServiceOnReservations()!=null)
        hairService.setHairServiceOnReservations(hairServiceDto.getHairServiceOnReservations());

        hairServiceRepo.save(hairService);
    }
}
