package com.bank.notification.service;

import com.bank.notification.dto.EmailDto;
import com.bank.notification.exception.NotificationSendException;
import com.bank.notification.model.EmailNotification;
import com.bank.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "account-notifications", groupId = "notification-group")
    public void sendEmailNotification(EmailDto emailDto){
        try {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.setMessage(emailDto.getMessage());
            emailNotification.setSubject(emailDto.getSubject());
            emailNotification.setToEmail(emailDto.getToEmail());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDto.getToEmail());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getMessage());
            javaMailSender.send(message);

            notificationRepository.save(emailNotification);
        }catch (Exception ex){
            throw new NotificationSendException(ex.getMessage());
        }
    }
}
