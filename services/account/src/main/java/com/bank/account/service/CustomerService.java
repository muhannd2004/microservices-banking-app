package com.bank.account.service;

import com.bank.account.dto.CustomerRequest;
import com.bank.account.dto.CustomerResponse;
import com.bank.account.exception.CustomerAlreadyExistsException;
import com.bank.account.exception.ResourceNotFoundException;
import com.bank.account.mapper.CustomerMapper;
import com.bank.account.model.Customer;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse createCustomer(String tokenId, CustomerRequest request) {
        customerRepository.findByMobileNumber(request.getMobileNumber()).ifPresent(c -> {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with mobile: " + request.getMobileNumber());
        });
        Customer customer = customerMapper.toEntity(request);
        customer.setTokenId(tokenId);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerResponse fetchCustomer(String tokenId) {
        return customerMapper.toDto(findByTokenId(tokenId));
    }

    @Transactional
    public CustomerResponse updateCustomer(String tokenId, CustomerRequest request) {
        Customer customer = findByTokenId(tokenId);
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Transactional
    public void deleteCustomer(String tokenId) {
        Customer customer = findByTokenId(tokenId);
        accountRepository.deleteByCustomerId(customer.getId());
        customerRepository.delete(customer);
    }

    private Customer findByTokenId(String tokenId) {
        return customerRepository.findByTokenId(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "tokenId", tokenId));
    }
}
