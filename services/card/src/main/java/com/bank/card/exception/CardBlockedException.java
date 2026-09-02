package com.bank.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardBlockedException extends RuntimeException {
    public CardBlockedException(Long cardNumber) {
        super("Card ending in " + String.valueOf(cardNumber).substring(12) + " is permanently blocked");
    }
}
