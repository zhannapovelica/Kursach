package com.example.kurs.services;

import com.example.kurs.models.Reception;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendDeclineEmail(String toEmail, Reception reception, String reason) {
        String str = "Ваш запис на " + reception.getCourse().getName() + " Відхилено \n" + " Дата проведення: " + reception.getDate()
                + " \n Причина відхилення: " + reason;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("dronkat45@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("subject");
            helper.setText(str, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.getMessage();
        }
        return str;
    }
}