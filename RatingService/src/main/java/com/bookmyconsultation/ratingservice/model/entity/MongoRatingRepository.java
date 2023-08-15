package com.bookmyconsultation.ratingservice.model.entity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRatingRepository extends MongoRepository<Rating, String> {
}
