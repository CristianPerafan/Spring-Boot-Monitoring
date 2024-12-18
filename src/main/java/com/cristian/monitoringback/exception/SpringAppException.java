package com.cristian.monitoringback.exception;

import com.cristian.monitoringback.enums.SpringAppExceptionType;
import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
public class SpringAppException extends RuntimeException{
    private final SpringAppExceptionType springAppExceptionType;

    public SpringAppException(@Nonnull SpringAppExceptionType springAppExceptionType) {
        super(springAppExceptionType.getMessage());
        this.springAppExceptionType = springAppExceptionType;
    }
}
