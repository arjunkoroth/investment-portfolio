package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

/**
* 
* @author Team-1
*
*/
@Data
@Builder
public class StockDTO {
	private String stockCode;
	private Double stockPrice;
	public StockDTO(String stockCode, Double stockPrice) {
		super();
		this.stockCode = stockCode;
		this.stockPrice = stockPrice;
	}
	public StockDTO() {
		super();		
	}	
}