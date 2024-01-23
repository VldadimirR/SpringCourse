package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final DataProcessingService dataProcessingService;

    private final RegistrationService registrationService;

    @Autowired
    public UserController(DataProcessingService dataProcessingService, RegistrationService registrationService) {
        this.dataProcessingService = dataProcessingService;
        this.registrationService = registrationService;
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(dataProcessingService.getAllUser(), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam int age, @RequestParam String email) {
        return new ResponseEntity<>(registrationService.registrationUser(name, age, email), HttpStatus.CREATED);
    }

}
