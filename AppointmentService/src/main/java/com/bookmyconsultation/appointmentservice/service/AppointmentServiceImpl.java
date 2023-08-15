package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import com.bookmyconsultation.appointmentservice.model.entity.Prescription;
import com.bookmyconsultation.appointmentservice.dao.AppointmentDAO;
import com.bookmyconsultation.appointmentservice.model.entity.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    public AppointmentDAO appointmentDao;

    @Autowired
    public PrescriptionRepository prescriptionRepository;

    public AppointmentEntity saveAppointment(AppointmentEntity appointment){
        return appointmentDao.save(appointment);
    }

    public AppointmentEntity findById(String id) {

        Optional<AppointmentEntity> obj = appointmentDao.findById(id);

        if(!obj.isPresent())
            return null;

        return obj.get();
    }

    public List<AppointmentEntity> findByUserId(String id){
        return appointmentDao.findByUserid(id);
    }

    public Prescription saveOrUpdatePrescription(Prescription prescription){
        return prescriptionRepository.save(prescription);
    }
}
