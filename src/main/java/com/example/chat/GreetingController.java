package com.example.chat;

import com.example.config.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    private final WebSocketService service;

    public GreetingController(WebSocketService service) {
        this.service = service;
    }

    // @MessageMapping 注释确保，如果消息发送到 /hello 目的地，则调用 greeting() 方法。
    // @SendTo 注释中指定 返回值广播给 /topic/greetings 的所有订阅者
    @MessageMapping("/chat")
    public void transport(@Payload ChatMessage message) {
        service.transport(message);
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "PONG";
    }
}
