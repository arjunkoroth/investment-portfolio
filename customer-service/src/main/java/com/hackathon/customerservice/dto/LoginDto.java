package com.hackathon.customerservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDto {
    @NotNull(message = "Customer Id can't be empty")
    private String customerId;

    @NotNull(message = "Password can't be empty")
    @Size(min = 6, message = "Password should have atleast 6 characters")
    private String password;
}
