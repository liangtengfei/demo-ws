package com.example.config;

import com.example.chat.ChatMessage;
import com.example.chat.ChatResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate template;

    public WebSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void transport(ChatMessage message) {
        if ("N".equals(message.getReceiver())) {
            broadcast(message);
        } else {
            sendToUser(message);
        }
    }

    public void sendToUser(ChatMessage message) {
        ChatResponseMessage res = new ChatResponseMessage();
        res.setName(message.getName());
        res.setReceiver(message.getReceiver());
        res.setContent(message.getContent());
        res.setSendTime(new Date());
        template.convertAndSendToUser(message.getReceiver(), "/topic/point", res);
    }

    public void broadcast(ChatMessage message) {
        ChatResponseMessage res = new ChatResponseMessage();
        res.setName(message.getName());
        res.setReceiver(message.getReceiver());
        res.setContent(message.getContent());
        res.setSendTime(new Date());
        template.convertAndSend("/topic/public", res);
    }
}
