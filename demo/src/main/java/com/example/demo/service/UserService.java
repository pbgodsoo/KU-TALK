package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 현재 인증된 사용자 정보를 반환하는 메서드
    public UserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsername(username);
    }

    // 사용자의 정보를 업데이트하는 메서드
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    // 사용자를 탈퇴시키는 메서드 (ID로 사용자 삭제)
    public boolean withdrawUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean withdrawUserByUsername(String username) {
        try {
            userRepository.deleteByUsername(username);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 이메일을 통해 사용자 이름을 찾는 메서드
    public String findUsernameByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null ? user.getUsername() : null;
    }
}
