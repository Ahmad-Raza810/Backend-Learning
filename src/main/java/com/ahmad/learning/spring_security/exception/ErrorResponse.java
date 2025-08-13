package com.ahmad.learning.spring_security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private int statuscode;
    private LocalDateTime dateTime;

    public ErrorResponse(String message, int statuscode, LocalDateTime dateTime) {
        this.message = message;
        this.statuscode = statuscode;
        this.dateTime = dateTime;
    }
}
