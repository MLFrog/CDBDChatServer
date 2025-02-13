package com.cdbd.chat.domain.chatmessage;

import java.time.Instant;

import com.cdbd.chat.domain.common.DomainEntity;

import lombok.Data;

@Data
@DomainEntity
public class ChatMessage {
	// DB Id
	private Long Id;
	private String roomId;
	// 외부 데이터 사용
	private String userId;
	private String content;
	private Instant createdAt;
	
	// 메시지 1개 가져오기
	public void fetchMessage(ChatMessageRepository repository) {
		
	}
	// 모든 메시지 가져오기
	
	// 메시지 삭제
	
	// 메시지 저장
}
