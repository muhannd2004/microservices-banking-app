package com.bank.card.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "token_id", nullable = false, length = 36)
    private String tokenId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "card_type", nullable = false, length = 20)
    private String cardType;

    @Column(name = "card_status", nullable = false, length = 20)
    private String cardStatus;

    @Column(nullable = false, length = 60)
    private String pin;

    @Column(nullable = false, length = 60)
    private String cvv;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "daily_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal dailyLimit;

    @Column(name = "daily_spent", nullable = false, precision = 15, scale = 2)
    private BigDecimal dailySpent;
}
