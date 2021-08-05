package com.hackathon.stockservice.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {

    private int errorCode;
    private String errorMessage;
}
