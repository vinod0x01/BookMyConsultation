package com.bookmyconsultation.doctorservice.model.entity;

import com.bookmyconsultation.doctorservice.model.dto.DoctorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Document(collection = "Doctor")
public class Doctor {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String mobile;
    private String dob;
    private String emailId;
    private String pan;
    private String specialization;

    private String status;
    private String registrationDate;

    private String approvedBy;
    private String approverComments;
    private String verificationDate;

    private int rating;

    public Doctor() {}

    public Doctor(Doctor doc)
    {
        this.id = doc.getId();
        this.firstName = doc.getFirstName();
        this.lastName = doc.getLastName();
        this.mobile = doc.getMobile();
        this.dob = doc.getMobile();
        this.emailId = doc.getEmailId();
        this.pan = doc.getPan();
        this.specialization = doc.getSpecialization();
        this.status = doc.getStatus();
        this.registrationDate = doc.getRegistrationDate();
        this.approvedBy = doc.getApprovedBy();
        this.approverComments = doc.getApproverComments();
        this.verificationDate = doc.getVerificationDate();
    }

    public Doctor(String id, String firstName, String lastName, String mobile, String dob, String emailId, String pan, String specialization, String status, String registrationDate, String approvedBy, String approverComments, String verificationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.dob = dob;
        this.emailId = emailId;
        this.pan = pan;
        this.specialization = specialization;
        this.status = status;
        this.registrationDate = registrationDate;
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
        this.verificationDate = verificationDate;
    }

    public Doctor(DoctorDTO doctorInfoDTO) {
        this.id = doctorInfoDTO.getId();
        this.firstName = doctorInfoDTO.getFirstName();
        this.lastName = doctorInfoDTO.getLastName();
        this.mobile = doctorInfoDTO.getMobile();
        this.dob = doctorInfoDTO.getDob();
        this.emailId = doctorInfoDTO.getEmailId();
        this.pan = doctorInfoDTO.getPan();
        this.specialization = doctorInfoDTO.getSpecialization();
        this.status = doctorInfoDTO.getStatus();
        this.registrationDate = doctorInfoDTO.getRegistrationDate();
        this.approvedBy = "Pending";
        this.approverComments = "Pending";
        this.verificationDate = "Pending";
    }

    public void approveDoctor(String approvedBy, String approverComments)
    {
        this.status = "Active";
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
        this.verificationDate = new SimpleDateFormat("dd-MM-YYYY").format(Calendar.getInstance().getTime());
    }

    public void rejectDoctor(String approvedBy, String approverComments)
    {
        this.status = "Rejected";
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
        this.verificationDate = new SimpleDateFormat("dd-MM-YYYY").format(Calendar.getInstance().getTime());
    }

    public void updateRating(int rating)
    {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(mobile, doctor.mobile) && Objects.equals(dob, doctor.dob) && Objects.equals(emailId, doctor.emailId) && Objects.equals(pan, doctor.pan) && Objects.equals(specialization, doctor.specialization) && Objects.equals(status, doctor.status) && Objects.equals(registrationDate, doctor.registrationDate) && Objects.equals(approvedBy, doctor.approvedBy) && Objects.equals(approverComments, doctor.approverComments) && Objects.equals(verificationDate, doctor.verificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mobile, dob, emailId, pan, specialization, status, registrationDate, approvedBy, approverComments, verificationDate);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id:'" + id + '\'' +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", dob:'" + dob + '\'' +
                ", emailId:'" + emailId + '\'' +
                ", pan:'" + pan + '\'' +
                ", specialization:'" + specialization + '\'' +
                ", status:'" + status + '\'' +
                ", registrationDate:'" + registrationDate + '\'' +
                ", approvedBy:'" + approvedBy + '\'' +
                ", approverComments:'" + approverComments + '\'' +
                ", verificationDate:'" + verificationDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
