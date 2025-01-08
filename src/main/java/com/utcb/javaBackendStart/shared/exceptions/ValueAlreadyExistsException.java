package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ValueAlreadyExistsException extends AppSpecificException {
    public ValueAlreadyExistsException(String value, String key) {
        super("The value '" + value + "' already exists for " + key,
                ValueAlreadyExistsException.class.getSimpleName(),
                HttpStatus.CONFLICT,
                key,
                new ArrayList<>());
    }
}
