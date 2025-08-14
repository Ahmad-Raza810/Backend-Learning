package com.ahmad.learning.spring_security.dto;

import com.ahmad.learning.spring_security.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private String password;

    private String name;

    public static Customer dtoToCustomer(CustomerRequest request){
        Customer customer=new Customer();
        customer.setName(request.getName());
        customer.setPassword(request.getPassword());

        return customer;
    }
}
