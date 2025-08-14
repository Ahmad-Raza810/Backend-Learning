package com.ahmad.learning.spring_security.service;


import com.ahmad.learning.spring_security.dto.CustomerRequest;
import com.ahmad.learning.spring_security.dto.CustomerResponse;
import com.ahmad.learning.spring_security.entity.Customer;
import com.ahmad.learning.spring_security.exception.ResourceNotFoundException;
import com.ahmad.learning.spring_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder encoder;


    public List<CustomerResponse> getAllCustomer(){
       return customerRepository
               .findAll()
               .stream()
               .map(CustomerResponse::customerToDto)
               .toList();
    }

    public CustomerResponse getCustomerById(Long customerId){
        Customer customer=customerRepository
                .findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found with id '"+customerId));

      return   CustomerResponse.customerToDto(customer);

    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest){
        Customer customer=CustomerRequest.dtoToCustomer(customerRequest);
        customer.setRole("USER");
        customer.setPassword(encoder.encode(customer.getPassword()));
        return CustomerResponse.customerToDto(customerRepository.save(customer));
    }

}
