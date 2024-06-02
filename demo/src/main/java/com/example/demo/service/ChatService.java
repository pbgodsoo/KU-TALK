package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatRoom;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.ChatRoomRepository;

import java.util.List;
@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatMessage saveMessage(String messageContent, String senderId, ChatRoom chatRoom) {
        ChatMessage chatMessage = new ChatMessage(messageContent, senderId, chatRoom);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getMessages(Long roomId) {
        return chatMessageRepository.findByChatRoomId(roomId);
    }

    public ChatRoom findChatRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Chat room not found"));
    }
}
