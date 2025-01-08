package com.utcb.javaBackendStart.auth.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private final String token;
}
