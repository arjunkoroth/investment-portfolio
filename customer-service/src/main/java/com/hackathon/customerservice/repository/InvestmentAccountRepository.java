/**
 * 
 */
package com.hackathon.customerservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.UserDetail;

/**
 * @author Team-1
 *
 */
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {

	/**
	 * Return the object of the user account.
	 * @param accountNumber
	 * @return
	 */
	public Optional<InvestmentAccount> findByAccountNumber(final String accountNumber);
	
	
	/**
	 * Get InvestmentAccount based on the account number and user details.
	 * @param accountNumber
	 * @param userDetail
	 * @return
	 */
	public Optional<InvestmentAccount> findByAccountNumberAndUserDetail(final String accountNumber , UserDetail userDetail);
}
