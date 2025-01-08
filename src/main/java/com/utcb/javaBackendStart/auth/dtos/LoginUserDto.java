package com.utcb.javaBackendStart.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginUserDto {
    @NotNull
    @Size(min = 5)
    @Email
    private final String email;
    @NotNull
    @Size(min = 7)
    private final String password;
}
