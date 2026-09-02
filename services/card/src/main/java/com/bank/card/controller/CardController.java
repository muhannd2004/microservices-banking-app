package com.bank.card.controller;

import com.bank.card.dto.*;
import com.bank.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
@Validated
@Tag(name = "Cards", description = "Card lifecycle management")
public class CardController {

    private final CardService cardService;

    @PostMapping
    @Operation(summary = "Issue a new card linked to a specific account")
    public ResponseEntity<CardResponse> issueCard(
            Authentication authentication,
            @Valid @RequestBody CardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardService.issueCard(authentication.getName(), request));
    }

    @GetMapping
    @Operation(summary = "Fetch all cards for the authenticated customer")
    public ResponseEntity<List<CardResponse>> fetchCards(Authentication authentication) {
        return ResponseEntity.ok(cardService.fetchCards(authentication.getName()));
    }

    @GetMapping("/{cardNumber}")
    @Operation(summary = "Fetch a specific card owned by the authenticated customer")
    public ResponseEntity<CardResponse> fetchCard(
            Authentication authentication,
            @PathVariable Long cardNumber) {
        return ResponseEntity.ok(cardService.fetchCard(authentication.getName(), cardNumber));
    }

    @PatchMapping("/{cardNumber}/status")
    @Operation(summary = "Freeze or unfreeze a card")
    public ResponseEntity<CardResponse> updateStatus(
            Authentication authentication,
            @PathVariable Long cardNumber,
            @Valid @RequestBody CardStatusRequest request) {
        return ResponseEntity.ok(cardService.updateStatus(
                authentication.getName(), cardNumber, request));
    }

    @PatchMapping("/{cardNumber}/limit")
    @Operation(summary = "Update the daily spending limit for a card")
    public ResponseEntity<CardResponse> updateLimit(
            Authentication authentication,
            @PathVariable Long cardNumber,
            @Valid @RequestBody LimitRequest request) {
        return ResponseEntity.ok(cardService.updateLimit(
                authentication.getName(), cardNumber, request));
    }

    @PostMapping("/{cardNumber}/validate-pin")
    @Operation(summary = "Validate PIN for ATM or payment use")
    public ResponseEntity<Void> validatePin(
            Authentication authentication,
            @PathVariable Long cardNumber,
            @Valid @RequestBody PinRequest request) {
        cardService.validatePin(authentication.getName(), cardNumber, request.getPin());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cardNumber}")
    @Operation(summary = "Permanently block a card (lost or stolen)")
    public ResponseEntity<Void> blockCard(
            Authentication authentication,
            @PathVariable Long cardNumber) {
        cardService.blockCard(authentication.getName(), cardNumber);
        return ResponseEntity.noContent().build();
    }
}
