package com.utcb.javaBackendStart.auth.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
public class UserDto {
    @NotNull
    private UUID id;

    private String displayName;

    @NotNull
    @Min(2)
    private String firstName;

    @NotNull
    @Min(2)
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private final RoleDto role;
}
