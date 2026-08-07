package com.bank.account.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Boolean communicationSw;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
