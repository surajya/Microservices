package com.sp.accounts.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AccountDto(
        @NotNull(message = "Customer Account Number can not be null or empty")
        @Min(value = 1000000000L,
                message = "Account number must be 10 digits")
        @Max(value = 9999999999L,
                message = "Account number must be 10 digits")
        Long accountNumber,

        @NotNull(message = "Account Type should not be null or empty")
        String accountType,

        @NotNull(message = "Branch Address should not be null or empty")
        String branchAddress
) {
}
