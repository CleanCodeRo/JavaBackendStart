package com.utcb.javaBackendStart.auth.dtos;

import com.utcb.javaBackendStart.constants.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {

    @Size(min = 2, message = "First name must be at least 2 characters long")
    @NotBlank(message = "First name is required and cannot be blank")
    private final String firstName;

    @Size(min = 2, message = "First name must be at least 2 characters long")
    @NotBlank(message = "Last name is required and cannot be blank")
    private final String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private final String email;

    @NotNull(message = "Role is required")
    private final RoleEnum role;
}
