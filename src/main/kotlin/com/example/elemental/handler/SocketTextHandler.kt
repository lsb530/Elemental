package com.example.elemental.handler

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap
class SocketTextHandler : TextWebSocketHandler() {
    private val sessions: MutableSet<WebSocketSession> = ConcurrentHashMap.newKeySet()

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val jsonObject: org.json.JSONObject = org.json.JSONObject(payload)
        for (s in sessions) {
            s.sendMessage(TextMessage("Hi " + jsonObject.getString("user") + "!"))
        }
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }
}