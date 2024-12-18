package com.cristian.monitoringback.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RequestExceptionType {
    NOT_FOUND(1, "Not found", HttpStatus.NOT_FOUND, LogLevel.INFO),
    MISSING_PATH_VARIABLE(2, "Missing path variable",HttpStatus.BAD_REQUEST,LogLevel.INFO),
    MISSING_REQUEST_PARAMETER(3, "Parameter is missing from request.",HttpStatus.BAD_REQUEST,LogLevel.INFO),
    ARGUMENT_NOT_VALID(4, "The received argument is not valid.",HttpStatus.BAD_REQUEST,LogLevel.INFO),
    CONSTRAINT_VIOLATION(5, "Constraint violation. It was not possible to process this request",HttpStatus.UNPROCESSABLE_ENTITY,LogLevel.INFO);

    private final int code;
    private final String message;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;
}
