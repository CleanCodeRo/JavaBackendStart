package com.utcb.javaBackendStart.auth.services;

import com.utcb.javaBackendStart.auth.dtos.*;
import com.utcb.javaBackendStart.auth.entities.PasswordResetCodeEntity;
import com.utcb.javaBackendStart.auth.entities.UserEntity;
import com.utcb.javaBackendStart.auth.models.PasswordResetCodeModel;
import com.utcb.javaBackendStart.auth.models.UserModel;
import com.utcb.javaBackendStart.auth.repositories.PasswordResetCodeRepository;
import com.utcb.javaBackendStart.auth.repositories.UserRepository;
import com.utcb.javaBackendStart.shared.dtos.SuccessDto;
import com.utcb.javaBackendStart.shared.exceptions.ResetPasswordCodeExpiredException;
import com.utcb.javaBackendStart.shared.exceptions.ValueNotFoundForPropertyException;
import com.utcb.javaBackendStart.shared.helpers.EmailComposer;
import com.utcb.javaBackendStart.shared.helpers.PasswordGenerator;
import jakarta.servlet.http.HttpServletRequest;
import com.utcb.javaBackendStart.auth.dtos.LoginResponseDto;
import com.utcb.javaBackendStart.auth.dtos.LoginUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthenticationService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetCodeRepository passwordResetCodeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public LoginResponseDto authenticate(LoginUserDto dto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String token = jwtService.generateToken(authentication);
        return LoginResponseDto.builder().token(token).build();
    }

    public SuccessDto resetPassword(ResetPasswordDto resetPasswordDto){
        PasswordResetCodeEntity passwordResetCodeEntity = passwordResetCodeRepository.findByEmailAndCode(resetPasswordDto.getEmail(), resetPasswordDto.getCode())
                .orElseThrow(() -> new ValueNotFoundForPropertyException(
                        "ResetPasswordCode",
                        "Email or Code",
                        resetPasswordDto.getEmail() + " " + resetPasswordDto.getCode())
                );

        if(passwordResetCodeEntity.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new ResetPasswordCodeExpiredException(resetPasswordDto.getEmail(), resetPasswordDto.getCode());
        }

        UserEntity userEntity = userRepository.findByEmail(resetPasswordDto.getEmail()).orElse(null);
        userEntity.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        userRepository.save(userEntity);

        passwordResetCodeEntity.setExpiresAt(passwordResetCodeEntity.getExpiresAt().minusDays(5));
        passwordResetCodeRepository.save(passwordResetCodeEntity);

        return SuccessDto.builder().success(true).build();
    }

    public SuccessDto requestResetPassword(RequestResetPasswordDto dto, HttpServletRequest httpServletRequest) {
        UserEntity userEntity = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ValueNotFoundForPropertyException("User", "Email", dto.getEmail()));
        PasswordResetCodeEntity passwordResetCodeEntity = passwordResetCodeRepository.findByEmailAndExpiresAtAfter(dto.getEmail(), LocalDateTime.now()).orElse(null);

        if(passwordResetCodeEntity == null || passwordResetCodeEntity.getExpiresAt().isBefore(LocalDateTime.now())){
            passwordResetCodeEntity = PasswordResetCodeModel.create(
                    dto.getEmail(),
                    PasswordGenerator.generatePassword(10),
                    LocalDateTime.now().plusDays(1),
                    UserModel.fromEntity(userEntity)
            ).toEntity();
        }

        SuccessDto isEmailSent = emailService.sendEmail(
                dto.getEmail(),
                "Reset account password for GIS",
                EmailComposer.createResetPasswordEmail(httpServletRequest, UserModel.fromEntity(userEntity) , passwordResetCodeEntity.getCode())
        );

        passwordResetCodeRepository.save(passwordResetCodeEntity);

        return SuccessDto.builder().success(isEmailSent.success()).build();
    }
}
