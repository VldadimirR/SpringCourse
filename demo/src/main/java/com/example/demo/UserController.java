package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final DataProcessingService dataProcessingService;

    private final RegistrationService registrationService;

    private final UserService userService;

    @Autowired
    public UserController(DataProcessingService dataProcessingService, RegistrationService registrationService, UserService userService) {
        this.dataProcessingService = dataProcessingService;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public String getAllUser(Model model){
        List<User> users = userService.getAllUser();

        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST)
    public String createUser(@RequestParam String name, @RequestParam int age, @RequestParam String email, Model model) {
         User user = registrationService.registrationUser(name, age, email);
        model.addAttribute("user", user);
        return "redirect:/user";
    }

}
