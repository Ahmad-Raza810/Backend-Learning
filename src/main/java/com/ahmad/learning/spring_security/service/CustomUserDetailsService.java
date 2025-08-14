package com.ahmad.learning.spring_security.service;

import com.ahmad.learning.spring_security.entity.Customer;
import com.ahmad.learning.spring_security.repository.CustomerRepository;
import com.ahmad.learning.spring_security.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND."));

        return new CustomUserDetail(customer);

    }

}
