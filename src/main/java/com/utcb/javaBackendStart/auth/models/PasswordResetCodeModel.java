package com.utcb.javaBackendStart.auth.models;

import com.utcb.javaBackendStart.auth.entities.PasswordResetCodeEntity;
import com.utcb.javaBackendStart.shared.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class PasswordResetCodeModel implements BaseModel<PasswordResetCodeModel, PasswordResetCodeEntity> {
    private final String email;
    private final String code;
    private final LocalDateTime expiresAt;
    private final UserModel user;


    public static PasswordResetCodeModel fromEntity(PasswordResetCodeEntity passwordResetCodeEntity){
        UserModel userModel = UserModel.fromEntity(passwordResetCodeEntity.getUser());

        return new PasswordResetCodeModel(
                passwordResetCodeEntity.getEmail(),
                passwordResetCodeEntity.getCode(),
                passwordResetCodeEntity.getExpiresAt(),
                userModel
        );
    }

    public static PasswordResetCodeModel create(String email, String code, LocalDateTime expiresAt, UserModel userModel){
        return new PasswordResetCodeModel(
                email,
                code,
                expiresAt,
                userModel
        );
    }

    @Override
    public PasswordResetCodeEntity toEntity() {
        return PasswordResetCodeEntity.builder()
                .email(this.email)
                .code(this.code)
                .expiresAt(this.expiresAt)
                .user(this.user.toEntity())
                .build();
    }
}
