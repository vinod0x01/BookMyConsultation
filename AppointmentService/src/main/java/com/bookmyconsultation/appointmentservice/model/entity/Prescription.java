package com.bookmyconsultation.appointmentservice.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document(collection = "Prescription")
public class Prescription {

    @Id
    private String appointmentId;

    private String doctorId;
    private String doctorName;
    private String userId;
    private String diagnosis;

    private String emailId;

    private List<Map<String,String>> medicineList;

    public Prescription() {}

    @Override
    public String toString() {
        return "Prescription{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", userId='" + userId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicineList=" + medicineList +
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Map<String, String>> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Map<String, String>> medicineList) {
        this.medicineList = medicineList;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
