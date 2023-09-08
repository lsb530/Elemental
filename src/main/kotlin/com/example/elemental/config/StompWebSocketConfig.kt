package com.example.elemental.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class StompWebSocketConfig : WebSocketMessageBrokerConfigurer
{
    // Broker & STOMP
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic") // 메시지 브로커 경로
        config.setApplicationDestinationPrefixes("/app") // 서버에 보낼 메시지의 prefix
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws-stomp")
            .withSockJS() // 웹소켓 엔드포인트
    }

}