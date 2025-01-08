package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class NameNotUniqueException extends AppSpecificException {
    public NameNotUniqueException(String entity, String name) {
        super("Name for the " + entity + "entity was already used", NameNotUniqueException.class.getSimpleName(), HttpStatus.BAD_REQUEST, name, new ArrayList<>());
    }
}
