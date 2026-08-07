package com.bank.account.mapper;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.AccountResponse;
import com.bank.account.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toDto(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setBranchAddress(account.getBranchAddress());
        response.setCommunicationSw(account.getCommunicationSw());
        response.setCreatedAt(account.getCreatedAt());
        response.setUpdatedAt(account.getUpdatedAt());
        return response;
    }

    public void updateEntity(Account account, AccountDto dto) {
        account.setAccountType(dto.getAccountType().name());
        account.setBranchAddress(dto.getBranchAddress());
    }
}
