package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class JwtNotValidException extends AppSpecificException {
    public JwtNotValidException(String jwt) {
        super("Authentication JWT is invalid", NameNotUniqueException.class.getSimpleName(), HttpStatus.BAD_REQUEST, jwt, new ArrayList<>());
    }
}
