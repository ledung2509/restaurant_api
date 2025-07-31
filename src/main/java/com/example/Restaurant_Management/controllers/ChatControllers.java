package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.websocket.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatControllers {

    private final SimpMessagingTemplate smt;

    public ChatControllers(SimpMessagingTemplate smt) {
        this.smt = smt;
    }

    @MessageMapping("chat")
    public void handleMessage(@Payload ChatMessage message) {
        smt.convertAndSend("/topic/messages", message);
    }
}