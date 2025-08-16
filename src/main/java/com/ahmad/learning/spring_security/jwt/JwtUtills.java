package com.ahmad.learning.spring_security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;


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
    public Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


    //method for extracting username
    public String extractUsername(String token){
       return  extractAllClaim(token).getSubject();
    }

    //method for check expiration of token.
    private Boolean isTokenExpired(String token){
        return extractAllClaim(token).getExpiration().before(new Date());
    }

    //method for generating token

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //method for validating jwt token



}
