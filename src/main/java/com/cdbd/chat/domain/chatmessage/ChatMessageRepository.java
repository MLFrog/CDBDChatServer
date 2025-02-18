package com.cdbd.chat.domain.chatmessage;

import java.util.List;

public interface ChatMessageRepository {
	ChatMessage getMessageById(Long id);
	List<ChatMessage> getMessagesByRoomId(String roomId);
	void saveMessage(ChatMessage data);
}
