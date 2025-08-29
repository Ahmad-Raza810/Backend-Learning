//package com.ahmad.learning.spring_security.config;
//
//
//import com.ahmad.learning.spring_security.entity.Customer;
//import com.ahmad.learning.spring_security.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AdminConfig implements CommandLineRunner {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        customerRepository.findByName("admin").orElse(
//                Customer customer=new Customer();
//                  customer.setName("admin");
//        customer.setPassword("1234");
//        customer.setRole("ADMIN");
//        )
//
//
//
//
//    }
//}
