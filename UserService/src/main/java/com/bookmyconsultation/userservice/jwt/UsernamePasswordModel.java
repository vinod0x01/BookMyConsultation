package com.bookmyconsultation.userservice.jwt;

import lombok.Data;

@Data
public class UsernamePasswordModel {
    private String username;
    private String password;
}
