package com.utcb.javaBackendStart.auth.controllers;


import com.utcb.javaBackendStart.auth.dtos.CreateUserDto;
import com.utcb.javaBackendStart.auth.dtos.UserDto;
import com.utcb.javaBackendStart.auth.services.UserService;
import com.utcb.javaBackendStart.shared.dtos.PageRequestDto;
import com.utcb.javaBackendStart.shared.dtos.PageResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.createUser(createUserDto, httpServletRequest));
    }


}
