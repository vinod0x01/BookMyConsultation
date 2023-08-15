package com.bookmyconsultation.appointmentservice.model.dto;

import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;

public class AppointmentReturnDTO {

    private String appointmentId;
    private String doctorId;
    private String userId;
    private String timeSlot;
    private String status;
    private String appointmentDate;

    public AppointmentReturnDTO() {}

    public AppointmentReturnDTO(AppointmentEntity appointment) {
        this.appointmentId = appointment.getAppointment_id();
        this.doctorId = appointment.getDoctor_id();
        this.userId = appointment.getUserid();
        this.timeSlot = appointment.getTimeslot();
        this.status = appointment.getStatus();
        this.appointmentDate = appointment.getAppointment_date();
    }

    public AppointmentReturnDTO(String appointmentId, String doctorId, String userId, String timeSlot, String status, String appointmentDate) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.timeSlot = timeSlot;
        this.status = status;
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "AppointmentReturnDTO{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", userId='" + userId + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", status='" + status + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
