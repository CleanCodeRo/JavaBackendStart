package com.utcb.javaBackendStart.auth.dtos;

import com.utcb.javaBackendStart.constants.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RoleDto {
    @NotNull
    private final UUID id;
    @NotNull
    private final RoleEnum name;
}
