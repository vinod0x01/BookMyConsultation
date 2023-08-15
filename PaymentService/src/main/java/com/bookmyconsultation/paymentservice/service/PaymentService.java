package com.bookmyconsultation.paymentservice.service;

import com.bookmyconsultation.paymentservice.model.dto.PaymentReturnDTO;

public interface PaymentService {

    PaymentReturnDTO makePayment(String id);

}
