package com.bank.account.controller;

import com.bank.account.dto.CustomerRequest;
import com.bank.account.dto.CustomerResponse;
import com.bank.account.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customers", description = "CRUD operations for customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Register a new customer")
    public ResponseEntity<CustomerResponse> createCustomer(
            Authentication authentication,
            @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(authentication.getName(), request));
    }

    @GetMapping
    @Operation(summary = "Fetch the authenticated customer's profile")
    public ResponseEntity<CustomerResponse> fetchCustomer(Authentication authentication) {
        return ResponseEntity.ok(customerService.fetchCustomer(authentication.getName()));
    }

    @PutMapping
    @Operation(summary = "Update the authenticated customer's profile")
    public ResponseEntity<CustomerResponse> updateCustomer(
            Authentication authentication,
            @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(authentication.getName(), request));
    }

    @DeleteMapping
    @Operation(summary = "Delete the authenticated customer and their data")
    public ResponseEntity<Void> deleteCustomer(Authentication authentication) {
        customerService.deleteCustomer(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
