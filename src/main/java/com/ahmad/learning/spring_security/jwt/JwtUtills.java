package com.ahmad.learning.spring_security.jwt;

import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.security.Keys;


public class JwtUtills{

    @Value("${secretkey}")
    private String key;

    @Value("${exptimeinms}")
    private String expTimeMs;


    //preparing signing key for generating signature
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(key.getBytes());

    }

    //method for extracting claims


    //method for validating token.
    //method for generating token

    //method for extracting username
    //method for creating signkey



}
