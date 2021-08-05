package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDetailsDTO {
	private String stockCode;
	private Integer quantity;
	private Double price;
	private Double value;
}
