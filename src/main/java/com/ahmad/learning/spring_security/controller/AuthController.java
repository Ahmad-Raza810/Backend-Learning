package com.ahmad.learning.spring_security.controller;


import com.ahmad.learning.spring_security.dto.CustomerRequest;
import com.ahmad.learning.spring_security.dto.CustomerResponse;
import com.ahmad.learning.spring_security.entity.Customer;
import com.ahmad.learning.spring_security.jwt.JwtUtills;
import com.ahmad.learning.spring_security.repository.CustomerRepository;
import com.ahmad.learning.spring_security.response.ApiResponse;
import com.ahmad.learning.spring_security.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final JwtUtills jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final CustomerRepository customerRepository;

    public AuthController(JwtUtills jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder encoder, CustomerRepository customerRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody CustomerRequest customerRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerRequest.getName(), customerRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(customerRequest.getName());


        LoginResponse response=new LoginResponse(
                "Login Successfully done.",
                token,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerResponse>> register(@RequestBody CustomerRequest customerRequest) throws AuthenticationException{

        if (customerRepository.findByName(customerRequest.getName()).isPresent()) {
            throw new RuntimeException("User with name '" + customerRequest.getName() + "' already exists");
        }

        Customer customer=new Customer();
        customer.setName(customerRequest.getName());
        customer.setPassword(encoder.encode(customerRequest.getPassword()));
        customer.setRole("USER");

        Customer savedCustomer=customerRepository.save(customer);


        ApiResponse<CustomerResponse> response=new ApiResponse<>(
                "Customer registered successfully",
                CustomerResponse.customerToDto(savedCustomer),
                true,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
