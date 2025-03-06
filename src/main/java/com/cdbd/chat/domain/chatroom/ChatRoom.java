package com.cdbd.chat.domain.chatroom;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.cdbd.chat.domain.common.DomainEntity;

import lombok.Data;

@Data
@DomainEntity
public class ChatRoom {
	
	// DB id
	private Long id;
	private ChatRoomId roomId;
	private String chatRoomName;
	private List<String> userList; 
	private Instant createdAt;
	private Instant updatedAt;
	
	// 채팅방 생성 --> Factory를 이용
	// 사람 강퇴(채팅방 퇴장) - 추후 User를 외부 api객체로 변환 필요
	public Boolean exitRoom(String user) {
		if (StringUtils.hasLength(user)) {
			return false;
		}
		
		this.userList.remove(user);
		this.updatedAt = Instant.now();
		return true;
	}
	
	// 사람 초대(채팅방 입장) - 추후 User를 외부 api객체로 변환 필요
	public Boolean joinRoom(String user) {
		if (StringUtils.hasLength(user)) {
			return false;
		}
		
		this.userList.add(user);
		this.updatedAt = Instant.now();
		return true;
	}
	
	// 공지사항
}
