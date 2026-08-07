package com.bank.account.dto;

import com.bank.account.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {
    @NotNull
    private AccountType accountType;

    @NotBlank
    private String branchAddress;
}
