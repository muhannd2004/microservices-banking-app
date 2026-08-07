package com.bank.account.mapper;

import com.bank.account.dto.CustomerRequest;
import com.bank.account.dto.CustomerResponse;
import com.bank.account.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        return customer;
    }

    public CustomerResponse toDto(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setMobileNumber(customer.getMobileNumber());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }
}
