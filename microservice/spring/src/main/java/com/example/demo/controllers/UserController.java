package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public Map<String, Object> submitForm(@RequestParam String name, @RequestParam int age, @RequestParam String city) {
        User user = new User(name, age, city);
        User savedUser = userService.saveUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Data saved successfully!");
        response.put("data", savedUser);
        return response;
    }
}