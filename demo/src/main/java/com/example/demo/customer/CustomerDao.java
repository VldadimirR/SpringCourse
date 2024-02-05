package com.example.demo.customer;

import java.util.Optional;

public interface CustomerDao {

    Optional<Customer> selectUserByEmail(String email);



}