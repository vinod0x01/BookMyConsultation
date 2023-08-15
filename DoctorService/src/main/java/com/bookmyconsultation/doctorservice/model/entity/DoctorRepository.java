package com.bookmyconsultation.doctorservice.model.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    Doctor findByFirstName(String firstName);


    Doctor findDoctorById(String id);

    List<Doctor> findAllByStatus(String status);

    List<Doctor> findAllByStatusAndSpecialization(String status, String specialization);

}
