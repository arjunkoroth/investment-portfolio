package com.hackathon.customerservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDto {
	
    @NotNull(message = "Customer Id can't be empty")
    @Size(min = 8, max = 30, message = "Customer Id should in between 8 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]\\w{5,29}$", message = "Customer Id should contain only alphabets and letters")
    private String customerId;

    @NotNull(message = "Password can't be empty")
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid Password. Password should have "
    		+ "1. Atleast one upper case character "
    		+ "2. Atlest one lower case "
    		+ "3. Atleast one digit "
    		+ "4. Atleast one special character "
    		+ "5. Should have a whitespace")
    private String password;
}
