package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatRoom;
import com.example.demo.service.ChatService;

@Controller
public class ChatMessageController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message) {
        System.out.println("Received message: " + message.getMessage());
        ChatRoom chatRoom = chatService.findChatRoomById(message.getChatRoom().getId());
        ChatMessage savedMessage = chatService.saveMessage(message.getMessage(), message.getSenderId(), chatRoom);
        System.out.println("Saved message: " + savedMessage.getMessage());
        messagingTemplate.convertAndSend("/topic/room/" + chatRoom.getId(), savedMessage);
    }
}