package com.example.elemental.config

import com.example.elemental.handler.WSHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.support.DefaultHandshakeHandler

@EnableWebSocket
@Configuration
class HandlerWebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val corsConfiguration = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
        }
        val corsConfigSource = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }
        registry.addHandler(WSHandler(), "/ws-handler")
            .setHandshakeHandler(DefaultHandshakeHandler())
            .setAllowedOrigins("*")
    }
}