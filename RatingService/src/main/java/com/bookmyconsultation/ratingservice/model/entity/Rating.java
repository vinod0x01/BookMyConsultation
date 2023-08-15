package com.bookmyconsultation.ratingservice.model.entity;

import com.bookmyconsultation.ratingservice.model.dto.RatingDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Rating")
public class Rating {

    @Id
    private String id;

    private String doctorId;
    private int rating;

    public Rating() {}

    public Rating(String id, String doctorId, int rating) {
        this.id = id;
        this.doctorId = doctorId;
        this.rating = rating;
    }

    public Rating(RatingDTO ratingDTO) {
        this.id = UUID.randomUUID().toString();
        this.doctorId = ratingDTO.getDoctorId();
        this.rating = ratingDTO.getRating();
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating && Objects.equals(id, rating1.id) && Objects.equals(doctorId, rating1.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, rating);
    }
}
