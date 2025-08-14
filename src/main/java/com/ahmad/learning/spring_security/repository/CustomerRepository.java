package com.ahmad.learning.spring_security.repository;


import com.ahmad.learning.spring_security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

   Optional<Customer> findByName(String username);
}
