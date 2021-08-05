/**
 * 
 */
package com.hackathon.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.userservice.entity.InvestmentAccount;

/**
 * @author Team-1
 *
 */
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {

}
