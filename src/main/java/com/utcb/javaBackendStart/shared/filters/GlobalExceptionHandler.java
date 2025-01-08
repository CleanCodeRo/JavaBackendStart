package com.utcb.javaBackendStart.shared.filters;

import com.utcb.javaBackendStart.shared.dtos.ErrorDto;
import com.utcb.javaBackendStart.shared.dtos.HttpExceptionDto;
import com.utcb.javaBackendStart.shared.exceptions.AppSpecificException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppSpecificException.class})
    protected ResponseEntity<Object> handleException(AppSpecificException exception, WebRequest request) {
        List<ErrorDto> errors = new ArrayList<>();
        if (exception.getValue() != null) {
            errors.add(new ErrorDto(exception.getName(), exception.getMessage(), exception.getValue()));
        } else if (exception.getErrors() != null && !exception.getErrors().isEmpty()) {
            exception.getErrors().forEach(e -> errors.add(new ErrorDto(e.getName(), e.getMessage(), e.getValue())));
        }
        HttpExceptionDto dto = new HttpExceptionDto(exception.getErrorCode().value(), exception.getName(), exception.getMessage(), errors);
        return handleExceptionInternal(exception, dto, new HttpHeaders(), HttpStatusCode.valueOf(dto.statusCode()), request);
    }
}
