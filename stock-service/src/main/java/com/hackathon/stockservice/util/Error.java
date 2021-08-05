package com.hackathon.stockservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public enum Error {
    STOCK_NOT_FOUND(404,"Stock cannot be found");
    private final int errorCode;
    private final String errorMessage;
}
