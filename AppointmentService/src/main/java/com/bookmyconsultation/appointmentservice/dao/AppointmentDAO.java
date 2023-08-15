package com.bookmyconsultation.appointmentservice.dao;

import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentDAO extends JpaRepository<AppointmentEntity, String> {

    List<AppointmentEntity> findByUserid(String user_id);

}
