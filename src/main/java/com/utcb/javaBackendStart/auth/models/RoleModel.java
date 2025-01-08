package com.utcb.javaBackendStart.auth.models;

import com.utcb.javaBackendStart.auth.dtos.RoleDto;
import com.utcb.javaBackendStart.auth.entities.RoleEntity;
import com.utcb.javaBackendStart.constants.RoleEnum;
import com.utcb.javaBackendStart.shared.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class RoleModel implements BaseModel<RoleModel, RoleEntity> {
    private UUID id;
    private RoleEnum name;

    public static RoleModel create(RoleDto roleDto){
        return new RoleModel(
                roleDto.getId(),
                roleDto.getName()
        );
    }

    public static RoleModel fromEntity(RoleEntity roleEntity){
        return new RoleModel(
                roleEntity.getId(),
                roleEntity.getName()
        );
    }

    @Override
    public RoleEntity toEntity(){
        return RoleEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
