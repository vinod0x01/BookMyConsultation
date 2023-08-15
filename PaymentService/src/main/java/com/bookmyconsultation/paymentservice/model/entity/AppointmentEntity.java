package com.bookmyconsultation.paymentservice.model.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="appointment")
public class AppointmentEntity {

    @Id
    @Column(name = "appointment_id", columnDefinition = "VARCHAR(64)")
    private String appointment_id;

    @Column(name = "appointment_date", columnDefinition="DATE")
    private String appointment_date;

    @Column(name = "created_date", columnDefinition="DATETIME")
    private String created_date;

    @Column(name = "doctor_id", columnDefinition = "VARCHAR(255)")
    private String doctor_id;

    @Column(name = "prior_medical_history", columnDefinition = "VARCHAR(255)")
    private String prior_medical_history;

    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    private String status;

    @Column(name = "symptoms", columnDefinition = "VARCHAR(255)")
    private String symptoms;

    @Column(name = "timeslot", columnDefinition = "VARCHAR(255)")
    private String timeslot;

    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
    private String userid;

    @Column(name = "user_email_id", columnDefinition = "VARCHAR(255)")
    private String user_email_id;

    @Column(name = "user_name", columnDefinition = "VARCHAR(255)")
    private String user_name;

    @Column(name = "doctor_name", columnDefinition = "VARCHAR(255)")
    private String doctor_name;

    public AppointmentEntity() {}

    public AppointmentEntity(String appointment_date, String doctor_id, String timeslot, String userid, String user_email_id, String user_name, String doctor_name) {
        this.appointment_id = UUID.randomUUID().toString();
        this.appointment_date = appointment_date;
        this.created_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        this.doctor_id = doctor_id;
        this.status = "PAYMENT_PENDING";
        this.timeslot = timeslot;
        this.userid = userid;
        this.user_email_id = user_email_id;
        this.user_name = user_name;
        this.doctor_name = doctor_name;
    }

    public AppointmentEntity(String appointment_id, String appointment_date, String created_date, String doctor_id, String prior_medical_history, String status, String symptoms, String timeslot, String userid, String user_email_id, String user_name, String doctor_name) {
        this.appointment_id = appointment_id;
        this.appointment_date = appointment_date;
        this.created_date = created_date;
        this.doctor_id = doctor_id;
        this.prior_medical_history = prior_medical_history;
        this.status = status;
        this.symptoms = symptoms;
        this.timeslot = timeslot;
        this.userid = userid;
        this.user_email_id = user_email_id;
        this.user_name = user_name;
        this.doctor_name = doctor_name;
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + appointment_id + '\'' +
                ", appointment_date='" + appointment_date + '\'' +
                ", created_date='" + created_date + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", prior_medical_history='" + prior_medical_history + '\'' +
                ", status='" + status + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", timeslot='" + timeslot + '\'' +
                ", user_id='" + userid + '\'' +
                ", user_email_id='" + user_email_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return Objects.equals(appointment_id, that.appointment_id) && Objects.equals(appointment_date, that.appointment_date) && Objects.equals(created_date, that.created_date) && Objects.equals(doctor_id, that.doctor_id) && Objects.equals(prior_medical_history, that.prior_medical_history) && Objects.equals(status, that.status) && Objects.equals(symptoms, that.symptoms) && Objects.equals(timeslot, that.timeslot) && Objects.equals(userid, that.userid) && Objects.equals(user_email_id, that.user_email_id) && Objects.equals(user_name, that.user_name) && Objects.equals(doctor_name, that.doctor_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointment_id, appointment_date, created_date, doctor_id, prior_medical_history, status, symptoms, timeslot, userid, user_email_id, user_name, doctor_name);
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String id) {
        this.appointment_id = id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPrior_medical_history() {
        return prior_medical_history;
    }

    public void setPrior_medical_history(String prior_medical_history) {
        this.prior_medical_history = prior_medical_history;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String user_id) {
        this.userid = user_id;
    }

    public String getUser_email_id() {
        return user_email_id;
    }

    public void setUser_email_id(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
}
