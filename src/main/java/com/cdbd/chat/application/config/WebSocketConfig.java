package com.cdbd.chat.application.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import com.cdbd.chat.application.handler.ChatWebSocketHandler;

@Configuration
public class WebSocketConfig {

    @Bean
    public HandlerMapping handlerMapping(ChatWebSocketHandler handler) {
        Map<String, Object> map = new HashMap<>();
        map.put("/chat", handler);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(-1);
        mapping.setUrlMap(map);

        return mapping;
    }
}