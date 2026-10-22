package com.bank.notification.controller;

import com.bank.notification.dto.EmailDto;
import com.bank.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@Validated
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "CRUD operations for bank notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/email")
    @Operation(summary = "Send an Email Notification to a user")
    public void send(@Valid @RequestBody EmailDto emailDto){
        notificationService.sendEmailNotification(emailDto);
    }
}
