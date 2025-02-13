package com.cdbd.chat.presentation.v1.api.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String sender;
    private String content;
}
