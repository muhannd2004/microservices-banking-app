package com.bank.card.repository;

import com.bank.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByTokenId(String tokenId);
    Optional<Card> findByCardNumberAndTokenId(Long cardNumber, String tokenId);

    @Query(value = "SELECT nextval('card_number_seq')", nativeQuery = true)
    String getNextUniqueCardSequence();
}
