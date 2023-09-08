package com.example.elemental.handler

import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler

class WSHandler: TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Received message: ${message.payload}")

        // 클라이언트에게 메시지 보내기
        session.sendMessage(TextMessage("Sever Reply(Handler): ${message.payload}"))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        super.handleMessage(session, message)
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        super.handleTransportError(session, exception)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
    }

    override fun supportsPartialMessages(): Boolean {
        return super.supportsPartialMessages()
    }

    override fun handlePongMessage(session: WebSocketSession, message: PongMessage) {
        super.handlePongMessage(session, message)
    }
}