package com.project.emailend.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emailend.entities.Email;
import com.project.emailend.entities.dtos.EmailDTO;
import com.project.emailend.services.EmailService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;


@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        emailService.sendEmail(email);
        
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
    
}
