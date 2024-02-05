package com.example.demo.customer;

import java.util.List;

public record CustomerDTO(
        Integer id,
        String name,
        String email,
        List<String> roles,
        String username
) {

}
