package com.bookmyconsultation.doctorservice.service;


import com.bookmyconsultation.doctorservice.model.entity.Doctor;

import java.util.List;

public interface MongoService {

/*    Doctor findByFirstName(String firstName);*/
    Doctor saveOrUpdateDoctor(Doctor doctor);
    List<Doctor> findByStatus(String status);
    List<Doctor> findAllByStatusAndSpecialization(String status, String specialization);


}
