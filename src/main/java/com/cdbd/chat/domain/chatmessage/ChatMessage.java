package com.cdbd.chat.domain.chatmessage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cdbd.chat.domain.common.DomainEntity;

import lombok.Data;

@Data
@DomainEntity
public class ChatMessage {
	// DB Id
	private Long id;
	private String roomId;
	// 외부 데이터 사용
	private String userId;
	private String content;
	private Instant createdAt;
	
	/**
	 * 메시지 1개 가져오기
	 * @param repository
	 * @return
	 */
	public ChatMessage fetchMessage(ChatMessageRepository repository) {
		ChatMessage data = repository.getMessageById(this.id);
		this.setRoomId(data.getRoomId());
		this.setUserId(data.getUserId());
		this.setContent(data.getContent());
		this.setCreatedAt(data.getCreatedAt());
		
		return this;
	}
	
	/**
	 * Room에 저장된 메시지 가져오기(채팅룸 도메인 기능으로 보여진다.)
	 * @param repository
	 * @return
	 */
	public List<ChatMessage> getMessageFromRoom(ChatMessageRepository repository) {
		return Optional.ofNullable(repository.getMessagesByRoomId(this.roomId))
				.orElse(new ArrayList<>());
	}
	
	/**
	 * 메시지 저장(1개)
	 * @param repository
	 */
	public void save(ChatMessageRepository repository) {
		repository.saveMessage(this);
	}
	
	// 메시지 입력
	public void inputMessage(String message) {
		
	}
}
