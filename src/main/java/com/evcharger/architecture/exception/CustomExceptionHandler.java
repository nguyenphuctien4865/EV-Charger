package com.evcharger.architecture.exception;

import com.evcharger.architecture.exception.core.ArchitectureException;
import com.evcharger.architecture.exception.core.ErrorResponse;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ArchitectureException.class)
    public final ResponseEntity<ErrorResponse> handleArchitectureException(ArchitectureException exception) {
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
            WebRequest webRequest) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setMsg(exception.getMessage());
        errorDetails.setTimestamp(new Date());
        errorDetails.setDetails(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        for (FieldError error : result.getFieldErrors()) {
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg(errorMessage.toString());
        errorResponse.setTimestamp(new Date());
        errorResponse.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<ErrorResponse> buildErrorResponse(ArchitectureException exception) {
        log.error("Exception occurred: Code: {}, Message: {}, Status: {}",
                exception.getCode(),
                exception.getMsg(),
                exception.getStatus(),
                exception); // Logs full stack trace

        ErrorResponse response = new ErrorResponse(exception);
        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }
}