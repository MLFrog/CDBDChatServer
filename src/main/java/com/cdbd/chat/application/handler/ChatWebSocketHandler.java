package com.cdbd.chat.application.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.cdbd.chat.domain.chatmessage.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler implements WebSocketHandler {
	
	private final ObjectMapper objectMapper;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String userId = generateUserId(); // 사용자 ID 생성 (UUID 등 사용)
        sessions.put(userId, session);

        // 클라이언트로부터 메시지 수신
        Mono<Void> receive = session.receive()
                .flatMap(message -> {
                    try {
                        ChatMessage chatMessage = objectMapper.readValue(message.getPayloadAsText(), ChatMessage.class);
                        chatMessage.setUserId(userId); // 메시지 작성자 설정
                        return broadcastMessage(chatMessage);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                })
                .then();

        // 클라이언트 연결 종료 처리
        Mono<Void> close = session.closeStatus()
                .doOnNext(status -> {
                    sessions.remove(userId);
                })
                .then();

        return Mono.zip(receive, close).then();
    }

    private Mono<Void> broadcastMessage(ChatMessage chatMessage) {
        byte[] bytes = toJson(chatMessage);

        return Flux.fromIterable(sessions.values())
            .flatMap(session -> {
            	WebSocketMessage message = new WebSocketMessage(WebSocketMessage.Type.BINARY, session.bufferFactory().wrap(bytes));
                return session.send(Mono.just(message)
                		.onErrorResume(e -> { // 오류 발생 시 처리
                            System.err.println("메시지 전송 중 오류 발생: " + e.getMessage());
                            return Mono.empty(); // 오류 무시
                }));
            })
            .then();
    }
    
    // ChatMessage 객체를 JSON 문자열로 변환
    private byte[] toJson(ChatMessage chatMessage) {
        try {
            return objectMapper.writeValueAsBytes(chatMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 사용자 ID 생성 (UUID 등 사용)
    private String generateUserId() {
        return java.util.UUID.randomUUID().toString();
    }
}
