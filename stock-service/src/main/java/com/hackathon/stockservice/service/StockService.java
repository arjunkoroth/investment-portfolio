/**
 * 
 */
package com.hackathon.stockservice.service;

import java.util.Optional;

import com.hackathon.stockservice.dto.StockDTO;

/**
 * Service contract for the operations provided by stocks.  
 * @author Team-1.
 *
 */
public interface StockService {

	/**
	 * Return the current price of the stock code.
	 * @param stockCode
	 * @return StockDTO
	 */
	public StockDTO getStockPrice(Optional<String> stockCode);
}