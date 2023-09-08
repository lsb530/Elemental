package com.example.elemental.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer, WebSocketConfigurer
{
    // Broker & STOMP
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic") // 메시지 브로커 경로
        config.setApplicationDestinationPrefixes("/app") // 서버에 보낼 메시지의 prefix
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket")
            .withSockJS() // 웹소켓 엔드포인트
    }

    // WebSocket
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val corsConfiguration = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
        }
        val corsConfigSource = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        registry.addHandler(myHandler(), "/my-websocket-endpoint")
            .setHandshakeHandler(DefaultHandshakeHandler())
            .setAllowedOrigins("*")
//        registry.addHandler(myHandler(), "/my-websocket-endpoint")
    }

    @Bean
    fun myHandler(): WebSocketHandler {
        return object : TextWebSocketHandler() {
            override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
                // 메시지를 받았을 때의 로직
                println("Received message: ${message.payload}")

                // 메시지를 다시 클라이언트로 전송
                session.sendMessage(TextMessage("Hello, ${message.payload}"))
            }
        }
    }
}