package com.example.config;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

public class UserChannelInterceptor implements ChannelInterceptor {
    private final Logger logger = LoggerFactory.getLogger(UserChannelInterceptor.class);

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        try {
            String name;
            StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
            StompCommand command = accessor.getCommand();
            if (StompCommand.CONNECT.equals(command)) {
                name = accessor.getNativeHeader("name").get(0);
                logger.info("正在连接用户：{}", name);
                accessor.setUser(new UserPrincipal(name));
            } else if (StompCommand.DISCONNECT.equals(command)) {
                logger.info("断开连接: {}", accessor.getUser().getName());
            }
        } catch (Exception e) {
            logger.error("出错了：", e);
        }
        return message;
    }
}
