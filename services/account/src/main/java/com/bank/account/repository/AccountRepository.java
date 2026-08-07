package com.bank.account.repository;

import com.bank.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
    Optional<Account> findByAccountNumberAndCustomerId(Long accountNumber, Long customerId);

    @Transactional
    void deleteByCustomerId(Long customerId);
}
