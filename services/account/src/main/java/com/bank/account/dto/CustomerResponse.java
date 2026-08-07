package com.bank.account.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
