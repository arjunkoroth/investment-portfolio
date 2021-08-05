package com.hackathon.customerservice.service;

import java.util.List;

import com.hackathon.customerservice.dto.AccountDTO;

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
}
