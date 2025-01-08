package com.utcb.javaBackendStart.auth.controllers;

import com.utcb.javaBackendStart.auth.dtos.LoginResponseDto;
import com.utcb.javaBackendStart.auth.dtos.LoginUserDto;
import com.utcb.javaBackendStart.auth.dtos.RequestResetPasswordDto;
import com.utcb.javaBackendStart.auth.dtos.ResetPasswordDto;
import com.utcb.javaBackendStart.auth.services.AuthenticationService;
import com.utcb.javaBackendStart.shared.dtos.SuccessDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody @Valid LoginUserDto dto) {
        return ResponseEntity.ok(this.authService.authenticate(dto));
    }

    @PostMapping("/passwordResetRequest")
    public ResponseEntity<SuccessDto> passwordResetRequest(@RequestBody @Valid RequestResetPasswordDto dto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.authService.requestResetPassword(dto, httpServletRequest));
    }

    @PostMapping("/passwordReset")
    public ResponseEntity<SuccessDto> passwordReset(@RequestBody @Valid ResetPasswordDto dto) {
        return ResponseEntity.ok(this.authService.resetPassword(dto));
    }

}
