package com.bank.card.mapper;

import com.bank.card.dto.CardResponse;
import com.bank.card.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardResponse toDto(Card card) {
        CardResponse response = new CardResponse();
        response.setMaskedCardNumber(mask(card.getCardNumber()));
        response.setCardType(card.getCardType());
        response.setCardStatus(card.getCardStatus());
        response.setExpiryDate(card.getExpiryDate());
        response.setDailyLimit(card.getDailyLimit());
        response.setDailySpent(card.getDailySpent());
        response.setCreatedAt(card.getCreatedAt());
        return response;
    }

    private String mask(Long cardNumber) {
        String num = String.valueOf(cardNumber);
        return "**** **** **** " + num.substring(num.length() - 4);
    }
}
