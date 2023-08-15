package com.bookmyconsultation.appointmentservice.model.entity;

import java.util.Objects;

public class Medicine {

    String name;
    String type;
    String dosage;
    String duration;
    String frequency;
    String remarks;

    public Medicine() {}

    public Medicine(String name, String type, String dosage, String duration, String frequency, String remarks) {
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        this.duration = duration;
        this.frequency = frequency;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MedicineList{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dosage='" + dosage + '\'' +
                ", duration='" + duration + '\'' +
                ", frequency='" + frequency + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(name, medicine.name) && Objects.equals(type, medicine.type) && Objects.equals(dosage, medicine.dosage) && Objects.equals(duration, medicine.duration) && Objects.equals(frequency, medicine.frequency) && Objects.equals(remarks, medicine.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, dosage, duration, frequency, remarks);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
