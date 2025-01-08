package com.utcb.javaBackendStart.shared.mappers;

import com.utcb.javaBackendStart.auth.dtos.UserDto;
import com.utcb.javaBackendStart.auth.models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

import static com.utcb.javaBackendStart.shared.mappers.RoleMapper.roleToDtoMapper;

public class UserMappers {
    public static UserDto userToDtoMapper(UserModel model) {
        return UserDto.builder()
                .id(model.getId())
                .displayName(model.getDisplayName())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .email(model.getEmail())
                .role(roleToDtoMapper(model.getRole()))
                .build();
    }

    public static List<UserDto> usersToDtoMapper(List<UserModel> models) {
        return models.stream().map(UserMappers::userToDtoMapper).collect(Collectors.toList());
    }
}
