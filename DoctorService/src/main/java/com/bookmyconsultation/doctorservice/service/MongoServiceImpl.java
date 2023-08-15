package com.bookmyconsultation.doctorservice.service;

import com.bookmyconsultation.doctorservice.model.entity.Doctor;
import com.bookmyconsultation.doctorservice.model.entity.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MongoServiceImpl implements MongoService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveOrUpdateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findByStatus(String status)
    {
        List<Doctor> allByStatus = doctorRepository.findAllByStatus(status);

        Collections.sort(allByStatus, Comparator.comparing(Doctor::getRating).reversed());
        return allByStatus;
    }

    @Override
    public List<Doctor> findAllByStatusAndSpecialization(String status, String specialization)
    {
        List<Doctor> allByStatusAndSpecialization = doctorRepository.findAllByStatusAndSpecialization(status, specialization);

        Collections.sort(allByStatusAndSpecialization, Comparator.comparing(Doctor::getRating).reversed());

        return allByStatusAndSpecialization;
    }

}
