package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityReturnDTO;
import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;
import com.bookmyconsultation.appointmentservice.dao.AvailabilityDao;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    public AvailabilityDao availabilityDao;

    public AvailabilityEntity saveAvailability(AvailabilityEntity availability){
        return availabilityDao.save(availability);
    }

    @Override
    public List<AvailabilityEntity> getAvailabilityByDoctorId(String doctorId) {
        List<AvailabilityEntity> availabilityEntities= availabilityDao.findAllByDoctorid(doctorId);
        return availabilityEntities;
    }
    public AvailabilityEntity getAvailabilityDetails(BigInteger id) {
        Optional<AvailabilityEntity> savedAvailability = availabilityDao.findById(Integer.parseInt(id.toString()));
        if (savedAvailability.isPresent()) {
            return savedAvailability.get();
        } else {
            throw new ResourceNotFoundException(id.toString());
        }
    }
    @Override
    public boolean deleteAvailability(BigInteger id) {
        AvailabilityEntity savedAvailability = getAvailabilityDetails(id);
        if(savedAvailability == null) {
            return false;
        }
        availabilityDao.delete(savedAvailability);
        return true;
    }

    @Override
    public AvailabilityReturnDTO findAllAvailabilityByDoctorId(String id) {

        List<AvailabilityEntity> availabilityEntities= availabilityDao.findAllByDoctorid(id);

        AvailabilityReturnDTO returnDTO = new AvailabilityReturnDTO();

        returnDTO.setDoctorId(id);

        String date;
        String time;

        for(AvailabilityEntity e : availabilityEntities)
        {
            System.out.println(e);
            date = e.getAvailability_date();
            time = e.getTimeslot();

            if(returnDTO.getAvailabilityMap() == null)
            {
                List<String> timing = new ArrayList<String>();
                timing.add(time);
                Map<String, List<String>> availabilityMap = new HashMap<>();
                availabilityMap.put(date, timing);
                returnDTO.setAvailabilityMap(availabilityMap);
            }
            else
            {
                if(returnDTO.getAvailabilityMap().get(date) == null)
                {
                    List<String> timing = new ArrayList<String>();
                    timing.add(time);
                    returnDTO.getAvailabilityMap().put(date, timing);
                }
                else
                {
                    returnDTO.getAvailabilityMap().get(date).add(time);
                }
            }
        }

        return returnDTO;
    }


}
