package com.bookmyconsultation.doctorservice.service;

import com.bookmyconsultation.doctorservice.model.entity.Doctor;
import com.bookmyconsultation.doctorservice.model.entity.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DoctorServiceImpl {
    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity updateRating(int rating, String doctorId){
        Doctor doctor = doctorRepository.findDoctorById(doctorId);

        if (Objects.isNull(doctor)) {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_DOCTOR_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        doctor.updateRating(rating);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }
}
