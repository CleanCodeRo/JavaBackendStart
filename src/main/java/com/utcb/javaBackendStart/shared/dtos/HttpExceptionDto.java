package com.utcb.javaBackendStart.shared.dtos;


import lombok.Builder;

import java.util.List;

@Builder
public record HttpExceptionDto(Integer statusCode, String name, String message, List<ErrorDto> errors) {
}
