package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRequestDto {
    private int numberOfStocks;
    private String stockCode;
}
