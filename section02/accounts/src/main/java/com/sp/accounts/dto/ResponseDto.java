package com.sp.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response Of customer api"
)
public record ResponseDto(
        @Schema(
                description = "status of response dto", example = "201"
        )
        String status,

        @Schema(
                description = "message of response dto", example = "operation successfully completed"
        )
        String message) {
}
