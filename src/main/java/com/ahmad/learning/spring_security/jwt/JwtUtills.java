package com.ahmad.learning.spring_security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtUtills{

    @Value("${secretkey}")
    private String key;

    @Value("${exptimeinms}")
    private Long expTimeMs;


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

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expTimeMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //method for validating jwt token
    public Boolean validateToken(String token, UserDetails userDetails){
         final String userName=extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //method for extracting token from header
    public String extractToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if (header!=null && header.startsWith("Bearer "))
        {
            return header.substring(7);
        }
        return null;
    }



}
