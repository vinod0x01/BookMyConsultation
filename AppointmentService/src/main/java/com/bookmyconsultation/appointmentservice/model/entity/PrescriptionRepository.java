package com.bookmyconsultation.appointmentservice.model.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
}
