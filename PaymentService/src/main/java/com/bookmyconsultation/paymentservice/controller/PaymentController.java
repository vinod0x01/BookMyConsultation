package com.bookmyconsultation.paymentservice.controller;

import com.bookmyconsultation.paymentservice.model.dto.PaymentReturnDTO;
import com.bookmyconsultation.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    /*Endpoint 1: This endpoint is responsible for making payments.

    URI: /payments?appointmentId*/
    @PostMapping()
    public ResponseEntity makePayment(@RequestParam(name="appointmentId", required = true) String appointmentId) {

        PaymentReturnDTO returnDTO = paymentService.makePayment(appointmentId);

        if(returnDTO == null)
        {
            String errorOutput = "{\"ERR_INVALID_APPOINTMENT_ID\"}";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PaymentReturnDTO>(returnDTO, HttpStatus.OK);
    }


}
