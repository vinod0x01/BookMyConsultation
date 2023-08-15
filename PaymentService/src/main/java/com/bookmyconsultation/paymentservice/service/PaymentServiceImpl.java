package com.bookmyconsultation.paymentservice.service;

import com.bookmyconsultation.paymentservice.dao.AppointmentDAO;
import com.bookmyconsultation.paymentservice.model.dto.PaymentReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Transactional
    @Override
    public PaymentReturnDTO makePayment(String id)
    {
        if(appointmentDAO.updatePaymentStatus(id) == 1)
        {
            PaymentReturnDTO paymentReturnDTO = new PaymentReturnDTO();
            paymentReturnDTO.setId(UUID.randomUUID().toString());
            paymentReturnDTO.setAppointmentId(id);
            paymentReturnDTO.setCreatedDate(new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(Calendar.getInstance().getTime()));
            return paymentReturnDTO;
        }

        return null;
    }

}
