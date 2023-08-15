package com.bookmyconsultation.paymentservice.jwt;

import lombok.Data;

@Data
public class UsernamePasswordModel {
    private String username;
    private String password;
}
