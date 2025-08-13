package com.ahmad.learning.spring_security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerForResourceNotFoundException(ResourceNotFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
