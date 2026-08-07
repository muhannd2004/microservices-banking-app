package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;
import com.bank.account.exception.ResourceNotFoundException;
import com.bank.account.mapper.AccountMapper;
import com.bank.account.model.Account;
import com.bank.account.model.Customer;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;


    @Transactional
    public AccountResponse createAccount(String tokenId, AccountRequest request) {
        Customer customer = findCustomerByToken(tokenId);
        Account account = new Account();
        account.setCustomerId(customer.getId());
        account.setAccountType(request.getAccountType().name());
        account.setBranchAddress(request.getBranchAddress());
        account.setCommunicationSw(true);
        return accountMapper.toDto(accountRepository.save(account));
    }

    public List<AccountResponse> fetchAccounts(String tokenId) {
        Customer customer = findCustomerByToken(tokenId);
        return accountRepository.findByCustomerId(customer.getId())
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Transactional
    public AccountResponse updateAccount(String tokenId, Long accountNumber, AccountDto request) {
        Customer customer = findCustomerByToken(tokenId);
        Account account = findOwnedAccount(accountNumber, customer.getId());
        accountMapper.updateEntity(account, request);
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Transactional
    public void deleteAccount(String tokenId, Long accountNumber) {
        Customer customer = findCustomerByToken(tokenId);
        accountRepository.delete(findOwnedAccount(accountNumber, customer.getId()));
    }

    @Transactional
    public AccountResponse fetchAccount(String tokenId, Long accountNumber) {
        Customer customer = findCustomerByToken(tokenId);
        return accountMapper.toDto(findOwnedAccount(accountNumber, customer.getId()));
    }

    private Customer findCustomerByToken(String tokenId) {
        return customerRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "tokenId", tokenId));
    }

    private Account findOwnedAccount(Long accountNumber, Long customerId) {
        return accountRepository.findByAccountNumberAndCustomerId(accountNumber, customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber",
                        accountNumber.toString()));
    }


}
