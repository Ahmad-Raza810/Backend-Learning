package com.ahmad.learning.spring_security.controller;


import com.ahmad.learning.spring_security.dto.CustomerRequest;
import com.ahmad.learning.spring_security.dto.CustomerResponse;
import com.ahmad.learning.spring_security.entity.Customer;
import com.ahmad.learning.spring_security.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CustomerController {


    @Autowired
    private CustomerService customerService;



    //endpoint for adding a customer .
    @PostMapping("customer")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestBody CustomerRequest customerRequest){
        CustomerResponse savedCustomer=customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }


    //endpoint for getting all customer
    @GetMapping("customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
       List<CustomerResponse> customerList=customerService.getAllCustomer();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    //endpoint for getting customer by id.
    @GetMapping("customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long customerId){
        CustomerResponse response=customerService.getCustomerById(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
