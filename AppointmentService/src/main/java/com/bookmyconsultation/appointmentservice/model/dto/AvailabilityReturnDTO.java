package com.bookmyconsultation.appointmentservice.model.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AvailabilityReturnDTO {

    private String doctorId;
    private Map<String, List<String>> availabilityMap;

    public AvailabilityReturnDTO() {}

    public AvailabilityReturnDTO(String doctorId, Map<String, List<String>> availabilityMap) {
        this.doctorId = doctorId;
        this.availabilityMap = availabilityMap;
    }

    @Override
    public String toString() {
        return "AvailabilityReturnDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", availabilityMap=" + availabilityMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityReturnDTO that = (AvailabilityReturnDTO) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(availabilityMap, that.availabilityMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, availabilityMap);
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Map<String, List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(Map<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }
}
