package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    private final UserRepository userRepository;

    public DataProcessingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getGreeting() {
        return "!!Hello, world!";
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}
