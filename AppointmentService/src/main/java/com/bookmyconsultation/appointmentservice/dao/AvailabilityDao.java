package com.bookmyconsultation.appointmentservice.dao;

import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityDao extends JpaRepository<AvailabilityEntity, Integer> {

    List<AvailabilityEntity> findAllByDoctorid(String id);

}
