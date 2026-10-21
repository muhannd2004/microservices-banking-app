package com.bank.notification.service;

import com.bank.notification.exception.NotificationSendException;
import com.bank.notification.model.EmailNotification;
import com.bank.notification.model.Notification;
import com.bank.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void sendEmailNotification(String to, String subject, String text){
        try {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.setMessage(text);
            emailNotification.setSubject(subject);
            emailNotification.setToEmail(to);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);

            notificationRepository.save(emailNotification);
        }catch (Exception ex){
            throw new NotificationSendException(ex.getMessage());
        }
    }
}
