package com.cristian.monitoringback.exception;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Generated
public class SpringAppErrorDetail extends SpringAppError {
    private final String detail;
}
