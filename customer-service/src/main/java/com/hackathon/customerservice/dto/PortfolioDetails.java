package com.hackathon.customerservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioDetails {

	/**
	 * 
	 */
	private Double totalPortfolioValue;
	
	/**
	 * 
	 */
	private Double balance ;
	
	/**
	 * 
	 */
	private List<StockDetailsDTO> stockDetails;
}
