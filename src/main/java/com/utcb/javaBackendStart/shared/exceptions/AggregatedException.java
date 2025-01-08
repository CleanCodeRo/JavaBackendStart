package com.utcb.javaBackendStart.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class AggregatedException extends AppSpecificException {
    public AggregatedException(String requestedValue, List<AppSpecificException> errors) {
        super(requestedValue, AggregatedException.class.getSimpleName(), HttpStatus.NOT_FOUND, null, errors);
    }
}
