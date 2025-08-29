package com.ahmad.learning.spring_security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private Boolean success;
    private Integer statuscode;
    private LocalDateTime dateTime;
}
