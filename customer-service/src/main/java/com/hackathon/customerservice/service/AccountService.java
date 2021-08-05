/**
 * 
 */
package com.hackathon.customerservice.service;

import java.util.List;
import java.util.Optional;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.dto.PortfolioDetails;

/**
 * 
 *
 * @author Team-1.
 *
 */
public interface AccountService {

	/**
	 * This method will return the number of investment account.
	 * @param customerId
	 * @return
	 */
	public List<AccountDTO> getAccounts(String customerId);
	
	/**
	 * Returns portfolio details for a provided account.
	 * @param accountNumber
	 * @return
	 */
	public PortfolioDetails getPortfolio(Optional<String> accountNumber);
}
