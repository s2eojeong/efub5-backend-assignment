package com.efub_assignment.community.community.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(
        @NotNull Long newOwnerId,
        @NotBlank String password
) {
}
