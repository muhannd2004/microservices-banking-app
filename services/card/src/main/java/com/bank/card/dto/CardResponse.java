package com.bank.card.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CardResponse {
    private String maskedCardNumber;
    private String cardType;
    private String cardStatus;
    private LocalDate expiryDate;
    private BigDecimal dailyLimit;
    private BigDecimal dailySpent;
    private LocalDateTime createdAt;
    private String cvv;
}
