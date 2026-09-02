package com.bank.card.dto;

import com.bank.card.enums.CardStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardStatusRequest {
    @NotNull
    private CardStatus status;
}
