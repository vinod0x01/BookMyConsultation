package com.example.notificationservice.entity;

import java.util.Objects;

public class Appointment {

    private String doctorName;
    private String date;
    private String timeSlot;
    private String status;
    private String emailId;

    public Appointment(String doctorName, String date, String timeSlot, String status,String emailId) {
        this.doctorName = doctorName;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
        this.emailId = emailId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
