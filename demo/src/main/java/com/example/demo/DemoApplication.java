package com.example.demo;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			CustomerRepository customerRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			createRandomCustomer(customerRepository,  passwordEncoder);
		};
	}

	private static void createRandomCustomer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		var faker = new Faker();
		Name name = faker.name();
		String firstName = name.firstName();
		String lastName = name.lastName();
		String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@raisbex.ru";
		Customer customer = new Customer(
				firstName +  " " + lastName,
				email,
				passwordEncoder.encode("password")
				);
		customerRepository.save(customer);
		System.out.println(email);
	}

}
