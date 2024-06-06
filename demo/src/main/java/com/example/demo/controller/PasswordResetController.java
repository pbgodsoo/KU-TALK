package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

import lombok.Getter;
import lombok.Setter;

@RestController
public class PasswordResetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/resetPassword")
    public String resetUserPassword(@RequestBody PasswordResetRequest request) {
        if (request.getNew_password() == null || request.getNew_password().isEmpty()) {
            return "Password cannot be null or empty";
        }

        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            String encodedPassword = passwordEncoder.encode(request.getNew_password());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "okk";
        
        }
        return "fail";
    }
}

@Getter
@Setter
class PasswordResetRequest {
    private String username;
    private String new_password;
}