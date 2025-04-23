package com.sarthak.customer.service;

import com.sarthak.customer.model.Customer;
import com.sarthak.customer.model.CustomerRequest;
import com.sarthak.customer.model.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer
                .builder()
                .id(request.id())
                .email(request.email())
                .address(request.address())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();


    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
