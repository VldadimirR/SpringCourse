package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    public void registerUser(User user) {
        System.out.println("A new user has been registration: " + user.getName());
    }
}
