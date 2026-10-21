package com.bank.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDto {
    @NotNull
    private String message;

    @NotNull
    private String subject;

    @NotNull
    private String toEmail;
}
