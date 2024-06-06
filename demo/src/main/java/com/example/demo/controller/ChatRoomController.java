package com.example.demo.controller;

import com.example.demo.model.ChatRoom;
import com.example.demo.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @PostMapping("/create")
    public ChatRoom createRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @GetMapping
    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ChatRoom getRoom(@PathVariable Long id) {
        Optional<ChatRoom> room = chatRoomRepository.findById(id);
        return room.orElse(null);
    }
}