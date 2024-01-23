package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    private final DataProcessingService dataProcessingService;

    private final NotificationService notificationService;

    public RegistrationService(UserService userService,
                               DataProcessingService dataProcessingService,
                               NotificationService notificationService) {
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
        this.notificationService = notificationService;
    }

    public User registrationUser(String name, int age, String email){
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUser(user);
        notificationService.registerUser(user);
        return user;
    }
}
