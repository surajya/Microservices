package com.sp.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponseDto",
        description = "ErrorResponseDto of Api Request in case error Condition"
)
public record ErrorResponseDto(
        @Schema(
                description = "api Path", example = "http://localhost:8080/create"
        )
        String apiPath,

        @Schema(
                description = "error code", example = "500"
        )
        HttpStatus errorCode,

        @Schema(
                description = "Error message", example = "Internal Server Error"
        )
        String errorMessage,

        @Schema(
                description = "Error Time stamp",
                example = "12 Aug 2026 12:08GMT5:30"
        )
        LocalDateTime errorTimestamp
) {
}
