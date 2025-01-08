package com.utcb.javaBackendStart.auth.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordDto {
    @NotNull
    private final String email;

    @NotNull
    private final String newPassword;

    @NotNull
    private final String code;
}
