package com.ahmad.learning.spring_security.service;


import com.ahmad.learning.spring_security.entity.Customer;
import com.ahmad.learning.spring_security.exception.ResourceNotFoundException;
import com.ahmad.learning.spring_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId){
        return customerRepository
                .findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found with id '"+customerId));

    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

}
