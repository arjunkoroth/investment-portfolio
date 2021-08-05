package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {

	/**
	 * 
	 */
	private String accountNumber;
	
	/**
	 * 
	 */
	private Double balance;
}
