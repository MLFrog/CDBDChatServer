package com.cdbd.chat.domain.chatmessage.repository;

import java.util.List;

import com.cdbd.chat.domain.chatmessage.ChatMessage;
import com.cdbd.chat.domain.chatmessage.ChatMessageRepository;

public class RedisChatMessageDomainRepository implements ChatMessageRepository {

	@Override
	public ChatMessage getMessageById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessage> getMessagesByRoomId(String roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMessage(ChatMessage data) {
		// TODO Auto-generated method stub
		
	}
	
}
