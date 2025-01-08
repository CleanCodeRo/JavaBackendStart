package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ValueNotFoundForPropertyException extends AppSpecificException {
    public ValueNotFoundForPropertyException(String entity, String valueName, String searchedForValue) {
        super("Could not find " + entity + " with this " + valueName, ValueNotFoundForPropertyException.class.getSimpleName(), HttpStatus.NOT_FOUND, searchedForValue, new ArrayList<>());
    }
}
