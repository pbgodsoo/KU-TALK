package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int maxUsers;

    @OneToMany(mappedBy = "chatRoom")
    @JsonManagedReference
    private List<ChatMessage> messages;


    //@OneToMany(mappedBy = "chatRoom")
    //@JsonManagedReference
    //private List<ChatMessage> messages;
}