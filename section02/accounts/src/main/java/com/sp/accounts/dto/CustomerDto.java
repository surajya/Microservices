package com.sp.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotNull(message = "Customer name can not be null or empty")
        @Size(min = 5, max = 30, message = "Customer name size should be between 5 and 30")
        String name,

        @Email(message = "Email address should be valid value")
        @NotNull(message = "Customer email can not be null or empty")
        String email,

        @NotNull(message = "Customer Mobile Number can not be null or empty")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number should be 10 digit")
        String mobileNumber,

        @Valid
        AccountDto accountDto
) {
}
