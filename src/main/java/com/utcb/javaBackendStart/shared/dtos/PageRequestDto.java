package com.utcb.javaBackendStart.shared.dtos;

import com.utcb.javaBackendStart.shared.constants.SortDirectionEnum;
import lombok.Builder;


@Builder
public record PageRequestDto(Integer pageNumber, Integer pageSize, SortDirectionEnum sortDirection, String sortBy) {
}
