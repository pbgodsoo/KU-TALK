package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> { // Long 타입 유지

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
    
    boolean existsByEmail(String email);

    void deleteByUsername(String username);
}
