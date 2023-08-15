package com.bookmyconsultation.paymentservice.dao;

import com.bookmyconsultation.paymentservice.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentDAO extends JpaRepository<AppointmentEntity, String> {

    @Modifying
    @Query("update AppointmentEntity app set app.status = 'Confirmed' where app.appointment_id = ?1")
    int updatePaymentStatus(String id);

}
