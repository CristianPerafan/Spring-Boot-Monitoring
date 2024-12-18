package com.cristian.monitoringback.exception;

import com.cristian.monitoringback.enums.RequestExceptionType;
import com.cristian.monitoringback.enums.SpringAppExceptionType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static String formatDetailMessage(List<FieldError> errors) {
        StringBuilder detailBuilder = new StringBuilder();
        if (!errors.isEmpty()) {
            for (int i = 0; i < errors.size(); i++) {
                detailBuilder.append("Error ").append(i + 1).append(": {").append(errors.get(i).getDefaultMessage()).append("} ");
            }
        }
        return detailBuilder.toString();
    }

    private static String buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
    }

    @ExceptionHandler
    public ResponseEntity<SpringAppError> handleException(SpringAppException ecoPhilException) {

        SpringAppExceptionType ecoPhilExceptionType = ecoPhilException.getSpringAppExceptionType();

        SpringAppError error = SpringAppError.builder()
                .code(ecoPhilExceptionType.getCode())
                .message(ecoPhilExceptionType.getMessage())
                .status(ecoPhilExceptionType.getResponseStatus())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable
            (MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        RequestExceptionType errorType = RequestExceptionType.MISSING_PATH_VARIABLE;

        SpringAppError error = SpringAppError.builder()
                .code(errorType.getCode())
                .message(ex.getVariableName() + errorType.getMessage())
                .status(errorType.getResponseStatus())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter
            (MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        RequestExceptionType errorType = RequestExceptionType.MISSING_REQUEST_PARAMETER;

        SpringAppError error = SpringAppError.builder()
                .code(errorType.getCode())
                .message(ex.getParameterName() + errorType.getMessage())
                .status(errorType.getResponseStatus())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        RequestExceptionType errorType = RequestExceptionType.ARGUMENT_NOT_VALID;

        SpringAppErrorDetail error = SpringAppErrorDetail.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .status(errorType.getResponseStatus())
                .detail(formatDetailMessage(ex.getBindingResult().getFieldErrors()))
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<SpringAppErrorDetail> handleConstraintViolation(ConstraintViolationException ex) {

        RequestExceptionType errorType = RequestExceptionType.CONSTRAINT_VIOLATION;

        SpringAppErrorDetail error = SpringAppErrorDetail.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .status(errorType.getResponseStatus())
                .detail(buildValidationErrors(ex.getConstraintViolations()))
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, error.getStatus());
    }
}
