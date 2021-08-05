package com.hackathon.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponseDto {
    private int statusCode;
    private String message;
}
