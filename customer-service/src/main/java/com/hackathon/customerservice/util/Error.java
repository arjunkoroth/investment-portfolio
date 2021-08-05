package com.hackathon.customerservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public enum Error {
    NOT_FOUND(404,"Not found"),
    INSUFFICIENT_BALANCE(409, "your account balance is low"),
    INCORRECT_CREDENTIALS(401, "Incorrect username or password");

    private final int errorCode;
    private final String errorMessage;
}
