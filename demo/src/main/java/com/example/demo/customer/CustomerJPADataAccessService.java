package com.example.demo.customer;

import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao{

    private  final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> selectUserByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}