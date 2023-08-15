package com.bookmyconsultation.appointmentservice.model.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AvailabilityDTO {

    private Map<String, List<String>> availabilityMap;

    public AvailabilityDTO() {}

    public AvailabilityDTO(Map<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "availabilityMap=" + availabilityMap +
                '}';
    }

    public Map<String, List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(Map<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityDTO that = (AvailabilityDTO) o;
        return Objects.equals(availabilityMap, that.availabilityMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availabilityMap);
    }
}
