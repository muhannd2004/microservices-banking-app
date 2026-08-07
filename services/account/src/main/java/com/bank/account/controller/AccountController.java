package com.bank.account.controller;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;
import com.bank.account.service.AccountService;
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
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Accounts", description = "CRUD operations for bank accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Open a new account with the specified type")
    public ResponseEntity<AccountResponse> createAccount(
            Authentication authentication,
            @Valid @RequestBody AccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(authentication.getName(), request));
    }

    @GetMapping
    @Operation(summary = "Fetch all accounts belonging to the authenticated customer")
    public ResponseEntity<List<AccountResponse>> fetchAccounts(Authentication authentication) {
        return ResponseEntity.ok(accountService.fetchAccounts(authentication.getName()));
    }

    @PutMapping("/{accountNumber}")
    @Operation(summary = "Update a specific account owned by the authenticated customer")
    public ResponseEntity<AccountResponse> updateAccount(
            Authentication authentication,
            @PathVariable Long accountNumber,
            @Valid @RequestBody AccountDto request) {
        return ResponseEntity.ok(accountService.updateAccount(
                authentication.getName(), accountNumber, request));
    }

    @DeleteMapping("/{accountNumber}")
    @Operation(summary = "Close a specific account owned by the authenticated customer")
    public ResponseEntity<Void> deleteAccount(
            Authentication authentication,
            @PathVariable Long accountNumber) {
        accountService.deleteAccount(authentication.getName(), accountNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountNumber}")
    @Operation(summary = "Fetch an account belonging to the customer")
    public ResponseEntity<AccountResponse> fetchAccount(
            Authentication authentication,
            @PathVariable Long accountNumber
    ){
        return ResponseEntity.ok(accountService.fetchAccount(authentication.getName(), accountNumber));
    }
}
