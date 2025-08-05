package com.example.Restaurant_Management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Điểm WebSocket endpoint client sẽ connect vào
        registry.addEndpoint("/websocket")
                .setAllowedOriginPatterns("*")  // Cho phép cross-origin nếu cần
                .withSockJS();  // fallback cho trình duyệt không hỗ trợ WebSocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Các topic server sẽ gửi tới client (server -> client)
        registry.enableSimpleBroker("/topic", "/queue");
        //client gửi đến server
        registry.setApplicationDestinationPrefixes("/app");
    }
}
