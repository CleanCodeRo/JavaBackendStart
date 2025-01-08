package com.utcb.javaBackendStart.shared.dtos;

import lombok.Builder;

@Builder
public record SuccessDto (
    boolean success
) {}
