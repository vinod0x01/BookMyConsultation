package com.bookmyconsultation.ratingservice.model.dto;


import java.util.Objects;

public class RatingDTO {

    private String doctorId;
    private int rating;

    public RatingDTO() {}

    public RatingDTO(String doctorId, int rating) {
        this.doctorId = doctorId;
        this.rating = rating;
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
    public String toString() {
        return "RatingDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO ratingDTO = (RatingDTO) o;
        return rating == ratingDTO.rating && Objects.equals(doctorId, ratingDTO.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, rating);
    }
}
