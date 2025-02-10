package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/flask-hello")
    public ResponseEntity<Map<String, Object>> callFlask() {
        Map<String, Object> response = new HashMap<>();

        try {
            // Call Flask microservice
            String flaskResponse = restTemplate.getForObject("http://localhost:5000/", String.class);

            response.put("message", "Spring Boot received response from Flask");
            response.put("flask_response", flaskResponse);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", "Failed to connect to Flask");
            response.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}