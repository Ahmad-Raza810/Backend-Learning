package com.ahmad.learning.spring_security.repository;


import com.ahmad.learning.spring_security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
