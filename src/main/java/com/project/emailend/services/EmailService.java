package com.project.emailend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.emailend.entities.Email;
import com.project.emailend.enums.StatusEmail;
import com.project.emailend.repositories.EmailRepository;

@Service
public class EmailService {
    
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email email){
        email.setSendDateEmail(LocalDateTime.now());
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SEND);

        }catch(MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally{
            return emailRepository.save(email);
        }
    }
}
