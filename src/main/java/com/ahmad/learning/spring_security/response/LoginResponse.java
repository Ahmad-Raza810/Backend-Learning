package com.ahmad.learning.spring_security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse{
    private String message;
    private String token;
    private Integer statuscode;
}
