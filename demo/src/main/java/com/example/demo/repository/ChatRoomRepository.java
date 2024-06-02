package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}