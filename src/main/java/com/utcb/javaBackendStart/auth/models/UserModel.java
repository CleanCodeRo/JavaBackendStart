package com.utcb.javaBackendStart.auth.models;

import com.utcb.javaBackendStart.auth.dtos.CreateUserDto;
import com.utcb.javaBackendStart.auth.entities.UserEntity;
import com.utcb.javaBackendStart.shared.helpers.PasswordGenerator;
import com.utcb.javaBackendStart.shared.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class UserModel implements BaseModel<UserModel, UserEntity> {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String displayName;
    private final RoleModel role;

    public static UserModel fromEntity(UserEntity entity) {
        RoleModel roleModel = RoleModel.fromEntity(entity.getRole());
        return new UserModel(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDisplayName(),
                roleModel
        );
    }

    @Override
    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(PasswordGenerator.generatePassword(10))
                .displayName(this.displayName)
                .role(this.role.toEntity())
                .build();
    }


    public static UserModel create(CreateUserDto userDto, RoleModel roleModel) {
        return new UserModel(
                UUID.randomUUID(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                null,
                roleModel
        );
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserModel userModel = (UserModel) obj;
        return id != null && id.equals(userModel.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
