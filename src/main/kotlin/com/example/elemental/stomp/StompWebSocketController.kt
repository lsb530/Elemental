package com.example.elemental.stomp

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class StompWebSocketController {
    @MessageMapping("/hello")  // 클라이언트에서 "/app/hello"로 메시지를 보냄
    @SendTo("/topic/greetings") // "/topic/greetings" 토픽으로 메시지를 브로드캐스트
    fun greeting(message: String): String {
        return "[STOMP]: $message"
    }
}