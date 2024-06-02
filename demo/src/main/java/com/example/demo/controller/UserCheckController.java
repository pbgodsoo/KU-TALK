package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
public class UserCheckController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserCheckController.class);

    @PostMapping("/usernameCheck")
    public ResponseEntity<String> checkUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        logger.info("Received username check request for: {}", username);
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("username is missing");
        }
        boolean exists = userRepository.existsByUsername(username);
        logger.info("Username {} exists: {}", username, exists);
        if (exists) {
            return ResponseEntity.ok("unavailable");
        } else {
            return ResponseEntity.ok("available");
        }
    }

    @PostMapping("/emailCheck")
    public ResponseEntity<String> checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        logger.info("Received email check request for: {}", email);
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("email is missing");
        }
        boolean exists = userRepository.existsByEmail(email);
        logger.info("Email {} exists: {}", email, exists);
        if (exists) {
            return ResponseEntity.ok("unavailable");
        } else {
            return ResponseEntity.ok("available");
        }
    }
}