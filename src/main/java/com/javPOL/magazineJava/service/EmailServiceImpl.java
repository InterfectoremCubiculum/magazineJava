package com.javPOL.magazineJava.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j // Automatycznie tworzy statyczny logger
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            log.debug("Sending mail");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom("MagazineJava@gmail.com");

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.debug("Error during sending email: {}",e.getMessage());
            throw new RuntimeException("Error during sending email: " + e.getMessage());
        }
    }
}