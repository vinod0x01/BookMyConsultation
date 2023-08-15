package com.example.notificationservice.controller;

import com.example.notificationservice.entity.User;
import com.example.notificationservice.service.SesEmailVerificationService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RequiredArgsConstructor
@RestController(value = "/")
public class NotificationController {

    private final SesEmailVerificationService verifyEmail;

    @GetMapping("verify")
    public ResponseEntity verifyEmail(@RequestParam("email") String emailId){
        verifyEmail.verifyEmail(emailId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("welcomeemail")
    public ResponseEntity sendEmail(@RequestBody User user) throws TemplateException, MessagingException, IOException {
        verifyEmail.sendVerificationEmail(user);
        return ResponseEntity.ok().build();
    }

}
