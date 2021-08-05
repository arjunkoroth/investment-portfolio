package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockDTO {
    private String stockCode;
    private double price;
}
