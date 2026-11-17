package com.sp.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(
        name = "Account",
        description = "Account of Customer"
)
public record AccountDto(
        @NotNull(message = "Customer Account Number can not be null or empty")
        @Min(value = 1000000000L,
                message = "Account number must be 10 digits")
        @Max(value = 9999999999L,
                message = "Account number must be 10 digits")
        @Schema(
                description = "Account Number of customer", example = "8234516789"
        )
        Long accountNumber,

        @NotNull(message = "Account Type should not be null or empty")
        @Schema(
                description = "Account Type of Customer", example = "Saving"
        )
        String accountType,

        @NotNull(message = "Branch Address should not be null or empty")
        @Schema(
                description = "branch address of customer account", example = "Thickset Varanasi, 221403"
        )
        String branchAddress
) {
}
