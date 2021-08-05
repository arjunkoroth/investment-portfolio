package com.hackathon.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.customerservice.entity.InvestmentAccount;

import java.util.Optional;

/**
 * @author Team-1
 *
 */
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {
    Optional<InvestmentAccount> findByAccountNumber(String accountNumber);
}
