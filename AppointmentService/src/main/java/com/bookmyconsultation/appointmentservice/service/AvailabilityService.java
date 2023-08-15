package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityReturnDTO;
import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;

import java.math.BigInteger;
import java.util.List;

public interface AvailabilityService {

    public AvailabilityEntity saveAvailability(AvailabilityEntity availability);

    public AvailabilityReturnDTO findAllAvailabilityByDoctorId(String id);
    public List<AvailabilityEntity> getAvailabilityByDoctorId(String id);

    boolean deleteAvailability( BigInteger id);
}
