/**
 * 
 */
package com.hackathon.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.customerservice.entity.InvestmentAccount;

/**
 * @author Team-1
 *
 */
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {

}
