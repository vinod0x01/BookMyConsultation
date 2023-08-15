package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import com.bookmyconsultation.appointmentservice.model.entity.Prescription;

import java.util.List;

public interface AppointmentService {

    public AppointmentEntity saveAppointment(AppointmentEntity appointment);

    public AppointmentEntity findById(String id);

    public List<AppointmentEntity> findByUserId(String id);

    Prescription saveOrUpdatePrescription(Prescription prescription);

}
