package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ResetPasswordCodeExpiredException extends AppSpecificException {
    public ResetPasswordCodeExpiredException(String email, String code) {
        super(
                "The reset password code for email '" + email + "' has expired or is invalid. Please request again to reset your password.",
                ResetPasswordCodeExpiredException.class.getSimpleName(),
                HttpStatus.GONE, // Status code GONE (410) better represents expired resources
                "Code: " + code + ", Email: " + email,
                new ArrayList<>()
        );
    }
}

