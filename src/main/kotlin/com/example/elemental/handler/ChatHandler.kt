package com.example.elemental.handler

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
@Component
class ChatHandler : TextWebSocketHandler() {
    companion object {
        private val list: MutableList<WebSocketSession> = ArrayList()
    }
    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        println("payload = $payload")
        for (sess in list) {
            sess.sendMessage(message)
        }
    }

    /* Client 접속*/
    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        list.add(session)
        println("$session 클라이언트 접속")
    }

    /* Client 접속 해제 */
    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("$session 클라이언트 접속 해제")
        list.remove(session)
    }

}