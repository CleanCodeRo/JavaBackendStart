package com.utcb.javaBackendStart.auth.services;

import com.utcb.javaBackendStart.auth.dtos.CreateUserDto;
import com.utcb.javaBackendStart.auth.dtos.RoleDto;
import com.utcb.javaBackendStart.auth.dtos.UserDto;
import com.utcb.javaBackendStart.auth.entities.UserEntity;
import com.utcb.javaBackendStart.auth.models.PasswordResetCodeModel;
import com.utcb.javaBackendStart.auth.models.RoleModel;
import com.utcb.javaBackendStart.auth.models.UserModel;
import com.utcb.javaBackendStart.auth.repositories.PasswordResetCodeRepository;
import com.utcb.javaBackendStart.auth.repositories.RoleRepository;
import com.utcb.javaBackendStart.auth.repositories.UserRepository;
import com.utcb.javaBackendStart.shared.dtos.SuccessDto;
import com.utcb.javaBackendStart.shared.exceptions.ValueNotFoundForPropertyException;
import com.utcb.javaBackendStart.shared.helpers.EmailComposer;
import com.utcb.javaBackendStart.shared.helpers.PasswordGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static com.utcb.javaBackendStart.shared.mappers.UserMappers.userToDtoMapper;


@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final PasswordResetCodeRepository passwordResetCodeRepository;

    public UserDto createUser(@Valid CreateUserDto createUserDto, HttpServletRequest httpServletRequest) {
        RoleDto roleDto = roleRepository.findRoleEntityByName(createUserDto.getRole()).orElseThrow(() -> new ValueNotFoundForPropertyException("Role", "Name", createUserDto.getRole().toString()));
        RoleModel roleModel = RoleModel.create(roleDto);

        UserModel userModel = UserModel.create(createUserDto, roleModel);
        UserEntity userEntity = userModel.toEntity();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserModel user = UserModel.fromEntity(userRepository.save(userEntity));

        PasswordResetCodeModel resetCodeModel = PasswordResetCodeModel.create(
                createUserDto.getEmail(),
                PasswordGenerator.generatePassword(10),
                LocalDateTime.now().plusDays(1),
                user
        );

        SuccessDto isEmailSent = emailService.sendEmail(
                createUserDto.getEmail(),
                "Your account has been created",
                EmailComposer.createSetPasswordEmail(httpServletRequest, user, resetCodeModel.getCode())
        );

        if(isEmailSent.success()){
            passwordResetCodeRepository.save(resetCodeModel.toEntity());
        }

        return userToDtoMapper(user);
    }
}

