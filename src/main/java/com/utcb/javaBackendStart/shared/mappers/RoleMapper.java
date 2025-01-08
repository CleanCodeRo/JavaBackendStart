package com.utcb.javaBackendStart.shared.mappers;

import com.utcb.javaBackendStart.auth.dtos.RoleDto;
import com.utcb.javaBackendStart.auth.models.RoleModel;

public class RoleMapper {

    public static RoleDto roleToDtoMapper(RoleModel model){
        return RoleDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}
