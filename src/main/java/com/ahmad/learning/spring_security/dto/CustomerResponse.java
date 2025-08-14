package com.ahmad.learning.spring_security.dto;

import com.ahmad.learning.spring_security.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String role;


    public static CustomerResponse customerToDto(Customer customer){
        CustomerResponse response=new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setRole(customer.getRole());

        return  response;
    }

}
