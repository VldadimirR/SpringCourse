package com.example.demo.customer;

import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                Math.toIntExact(customer.getId()),
                customer.getName(),
                customer.getEmail(),
                customer.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()),
                customer.getUsername()
        );
    }
}