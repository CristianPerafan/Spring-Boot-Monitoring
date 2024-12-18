package com.cristian.monitoringback.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SpringAppExceptionType {
    PRODUCT_NOT_FOUND(1, "Product not found", null, HttpStatus.NOT_FOUND, LogLevel.INFO);
    private final int code;
    private final String message;
    private final String parameterName;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;
}
