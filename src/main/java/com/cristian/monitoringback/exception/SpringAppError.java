package com.cristian.monitoringback.exception;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
@Generated
public class SpringAppError {
    private final int code;
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime time;
}
