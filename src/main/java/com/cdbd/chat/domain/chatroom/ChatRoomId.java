package com.cdbd.chat.domain.chatroom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class ChatRoomId {
	private final String value;
}
