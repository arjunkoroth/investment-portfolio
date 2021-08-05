package com.hackathon.stockservice.dto;

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
}
