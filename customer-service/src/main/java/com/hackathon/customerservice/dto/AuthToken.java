package com.hackathon.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthToken {

    private String token;
    private String username;
}
