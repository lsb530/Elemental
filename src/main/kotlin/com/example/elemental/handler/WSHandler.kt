package com.example.elemental.handler

import ch.qos.logback.core.testUtil.RandomUtil
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer
import kotlin.random.Random  // 이 부분을 추가


class WSHandler: TextWebSocketHandler() {

    private val CLIENTS = ConcurrentHashMap<String, WebSocketSession>()

    fun generateRandomKoreanNickname(): String {
        val firstWords = listOf("익명의", "배고픈", "즐거운", "행복한", "슬픈", "기쁜")
        val secondWords = listOf("너구리", "감자", "사과", "고양이", "토끼", "사람")

        val firstWord = firstWords[Random.nextInt(firstWords.size)]
        val secondWord = secondWords[Random.nextInt(secondWords.size)]

        return "$firstWord $secondWord"
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("[WS Handler] Received message: ${message.payload}")
        for (client in CLIENTS) {
            client.value.sendMessage(TextMessage("[WS Handler]: ${message.payload}"))
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("[WS Handler] New session, ${session.id}")
        CLIENTS[session.id] = session
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("[WS Handler] Close session, ${session.id}")
        CLIENTS.remove(session.id)
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        super.handleMessage(session, message)
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        super.handleTransportError(session, exception)
    }

    override fun supportsPartialMessages(): Boolean {
        return super.supportsPartialMessages()
    }

    override fun handlePongMessage(session: WebSocketSession, message: PongMessage) {
        super.handlePongMessage(session, message)
    }
}