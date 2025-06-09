package com.example.Restaurant_Management.websocket;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private String message;
    private String sender;
    private String timestamp;
}
