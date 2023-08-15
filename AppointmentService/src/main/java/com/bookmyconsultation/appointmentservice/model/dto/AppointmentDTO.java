package com.bookmyconsultation.appointmentservice.model.dto;

import java.util.Objects;

public class AppointmentDTO {

    private String doctorId;
    private String doctorName;
    private String userId;
    private String timeSlot;
    private String appointmentDate;

    public AppointmentDTO() {}

    public AppointmentDTO(String doctorId, String doctorName, String userId, String timeSlot, String appointmentDate) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.userId = userId;
        this.timeSlot = timeSlot;
        this.appointmentDate = appointmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDTO that = (AppointmentDTO) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(doctorName, that.doctorName) && Objects.equals(userId, that.userId) && Objects.equals(timeSlot, that.timeSlot) && Objects.equals(appointmentDate, that.appointmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, doctorName, userId, timeSlot, appointmentDate);
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", userId='" + userId + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
