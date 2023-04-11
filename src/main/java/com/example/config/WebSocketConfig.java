package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS()
                .setClientLibraryUrl("/chat/sockjs.min.js");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker() 以启用一个简单的基于内存的消息代理，以将问候消息传送回以 /topic 为前缀的目的地上的客户端
        registry.enableSimpleBroker("/topic", "/queue");
        // 默认就是/user
        registry.setUserDestinationPrefix("/user");
        // 它还为绑定到使用 @MessageMapping 注释的方法的消息指定 /app 前缀
        // 此前缀将用于定义所有消息映射。例如，/app/hello 是 GreetingController.greeting() 方法映射到处理的端点。
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor());
    }

    @Bean
    public UserChannelInterceptor channelInterceptor() {
        return new UserChannelInterceptor();
    }
}
