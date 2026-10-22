package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    @NotNull
    private String message;

    @NotNull
    private String subject;

    @NotNull
    private String toEmail;
}
