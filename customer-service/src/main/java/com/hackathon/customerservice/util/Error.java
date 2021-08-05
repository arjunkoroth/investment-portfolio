package com.hackathon.customerservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public enum Error {
    NOT_FOUND(404,"Not found"),
    INCORRECT_CREDENTIALS(401, "Incorrect username or password");

    private final int errorCode;
    private final String errorMessage;
}
