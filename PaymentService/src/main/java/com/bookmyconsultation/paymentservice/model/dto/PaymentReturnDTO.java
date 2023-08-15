package com.bookmyconsultation.paymentservice.model.dto;

public class PaymentReturnDTO {

    private String id;
    private String appointmentId;
    private String createdDate;

    public PaymentReturnDTO() {}

    public PaymentReturnDTO(String id, String appointmentId, String createdDate) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "PaymentReturnDTO{" +
                "id='" + id + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
