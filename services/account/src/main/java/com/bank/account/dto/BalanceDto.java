package com.bank.account.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalanceDto {
    @PositiveOrZero(message = "Balance cannot be negative")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;
}
