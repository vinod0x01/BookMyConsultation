package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityDTO;
import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;
import com.bookmyconsultation.appointmentservice.model.entity.Prescription;
import com.bookmyconsultation.appointmentservice.model.dto.AppointmentDTO;

import java.util.List;

public interface MainService {

    public boolean saveAvailability(String doctorId, AvailabilityDTO availabilityDTO);

    public List<AvailabilityEntity> getAppointments(String doctorId);

    public AppointmentEntity saveAppointment(AppointmentDTO appointmentDTO);

    public AppointmentEntity findById(String id);

    public Prescription newPrescription(Prescription prescription);

}
