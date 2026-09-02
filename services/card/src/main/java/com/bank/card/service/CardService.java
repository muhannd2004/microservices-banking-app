package com.bank.card.service;

import com.bank.card.dto.*;
import com.bank.card.enums.CardStatus;
import com.bank.card.exception.CardBlockedException;
import com.bank.card.exception.InvalidPinException;
import com.bank.card.exception.ResourceNotFoundException;
import com.bank.card.mapper.CardMapper;
import com.bank.card.model.Card;
import com.bank.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private static final String BANK_BIN = "400000";
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${card.validity.years}")
    private int validityYears;

    @Transactional
    public CardResponse issueCard(String tokenId, CardRequest request) {
        String rawCvv = generateCvv();
        Card card = new Card();
        card.setTokenId(tokenId);
        card.setCardNumber(generateCardNumber());
        card.setAccountId(request.getAccountId());
        card.setCardType(request.getCardType().name());
        card.setCardStatus(CardStatus.ACTIVE.name());
        card.setPin(passwordEncoder.encode(request.getPin()));
        card.setCvv(passwordEncoder.encode(rawCvv));
        card.setExpiryDate(LocalDate.now().plusYears(validityYears));
        card.setDailyLimit(request.getDailyLimit());
        card.setDailySpent(BigDecimal.ZERO);
        CardResponse response = cardMapper.toDto(cardRepository.save(card));
        response.setCvv(rawCvv);
        return response;
    }

    public List<CardResponse> fetchCards(String tokenId) {
        return cardRepository.findByTokenId(tokenId)
                .stream().map(cardMapper::toDto).toList();
    }

    public CardResponse fetchCard(String tokenId, Long cardNumber) {
        return cardMapper.toDto(findOwnedCard(tokenId, cardNumber));
    }

    @Transactional
    public CardResponse updateStatus(String tokenId, Long cardNumber, CardStatusRequest request) {
        Card card = findOwnedCard(tokenId, cardNumber);
        if (CardStatus.BLOCKED.name().equals(card.getCardStatus()))
            throw new CardBlockedException(cardNumber);
        if (request.getStatus() == CardStatus.BLOCKED)
            throw new CardBlockedException(cardNumber);
        card.setCardStatus(request.getStatus().name());
        return cardMapper.toDto(cardRepository.save(card));
    }

    @Transactional
    public CardResponse updateLimit(String tokenId, Long cardNumber, LimitRequest request) {
        Card card = findOwnedCard(tokenId, cardNumber);
        card.setDailyLimit(request.getDailyLimit());
        return cardMapper.toDto(cardRepository.save(card));
    }

    public void validatePin(String tokenId, Long cardNumber, String pin) {
        Card card = findOwnedCard(tokenId, cardNumber);
        if (!passwordEncoder.matches(pin, card.getPin()))
            throw new InvalidPinException();
    }

    @Transactional
    public void blockCard(String tokenId, Long cardNumber) {
        Card card = findOwnedCard(tokenId, cardNumber);
        card.setCardStatus(CardStatus.BLOCKED.name());
        cardRepository.save(card);
    }

    private Card findOwnedCard(String tokenId, Long cardNumber) {
        return cardRepository.findByCardNumberAndTokenId(cardNumber, tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber",
                        cardNumber.toString()));
    }

    private String generateCvv() {
        return String.format("%03d", secureRandom.nextInt(1000));
    }

    private Long generateCardNumber(){
        String cardNumberStr = BANK_BIN + cardRepository.getNextUniqueCardSequence();
        return Long.valueOf(cardNumberStr);
    }

}
