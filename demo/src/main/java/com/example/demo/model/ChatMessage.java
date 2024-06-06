package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.example.demo.entity.UserEntity;
//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime timestamp;
    private String senderId;

    @ManyToOne
    @JsonBackReference
    private ChatRoom chatRoom;

    // 기본 생성자
    public ChatMessage() {}

    // 필요한 필드를 포함한 생성자
    public ChatMessage(String message, String senderId, ChatRoom chatRoom) {
        this.message = message;
        this.timestamp = LocalDateTime.now();  // 메시지 생성 시각 설정
        this.senderId = senderId;
        this.chatRoom = chatRoom;
    }

    // getters and setters
}