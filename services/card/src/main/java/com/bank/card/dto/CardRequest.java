package com.bank.card.dto;

import com.bank.card.enums.CardType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardRequest {

    @NotNull
    private Long accountId;

    @NotNull
    private CardType cardType;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal dailyLimit;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4,6}$")
    private String pin;
}
