package com.example.elemental.socket

import com.example.elemental.config.ServerEndpointConfig
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.websocket.*
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListSet
import kotlin.random.Random


@ServerEndpoint(value = "/java-EE", configurator = ServerEndpointConfig::class)
@Service
class JavaEEWebSocketService {
    private val CLIENTS = ConcurrentHashMap<String, Session>()
    private val NICKNAMES = ConcurrentHashMap<String, String>()
    private val USED_NICKNAMES = ConcurrentSkipListSet<String>()

    fun generateRandomKoreanNickname(): String {
        val firstWords = listOf("익명의", "배고픈", "즐거운", "행복한", "슬픈", "기쁜", "똑똑한", "예쁜", "멋진", "용감한")
        val secondWords = listOf("너구리", "감자", "사과", "고양이", "토끼", "사람", "달팽이", "치타", "나무", "강아지")

        var nickname: String

        do {
            val firstWord = firstWords[Random.nextInt(firstWords.size)]
            val secondWord = secondWords[Random.nextInt(secondWords.size)]

            nickname = "$firstWord $secondWord"
        } while (USED_NICKNAMES.contains(nickname))

        USED_NICKNAMES.add(nickname)

        return nickname
    }

    @OnMessage
    @Throws(Exception::class)
    fun onMessage(message: String, session: Session) {
        println("[Java EE] Received message: ${message}")
        val nickname = NICKNAMES[session.id] ?: "익명"

        val messageData = "[Java EE - $nickname]: $message"
        val clients = CLIENTS.size  // 현재 클라이언트의 수
        val messagePayload = mapOf("nickname" to nickname, "message" to messageData, "type" to "user", "clients" to clients)
        val messageJSON = jacksonObjectMapper().writeValueAsString(messagePayload)

        for (client in CLIENTS) {
            client.value.basicRemote.sendText(messageJSON)
        }
    }

    @OnOpen
    fun onOpen(session: Session) {
        println("New session, ${session.id}")

        val nickname = generateRandomKoreanNickname()
        NICKNAMES[session.id] = nickname
        CLIENTS[session.id] = session

        val clients = CLIENTS.size  // 현재 클라이언트의 수

        val nickMessage = "Your nickname is $nickname"
        val nickMessagePayload = mapOf("nickname" to nickname, "message" to nickMessage, "type" to "user", "clients" to clients)
        val nickMessageJson = jacksonObjectMapper().writeValueAsString(nickMessagePayload)
        session.basicRemote.sendText(nickMessageJson)

        val broadCastMessage = "[Java EE - System]: $nickname has joined the chat."
        val broadCastPayload = mapOf("nickname" to nickname, "message" to broadCastMessage, "type" to "user", "clients" to clients)
        val messageJSON = jacksonObjectMapper().writeValueAsString(broadCastPayload)

        for (client in CLIENTS) {
            client.value.basicRemote.sendText(messageJSON)
        }
    }

    @OnClose
    @Throws(Exception::class)
    fun onClose(session: Session) {
        println("[Java EE] Close session, ${session.id}")

        val nickname = NICKNAMES.remove(session.id) ?: "익명" // 세션 정보를 먼저 제거
        USED_NICKNAMES.remove(nickname)  // 닉네임 재사용 가능하도록 제거
        CLIENTS.remove(session.id)

        val message = "[WS Handler - System]: $nickname has left the chat."
        val clients = CLIENTS.size  // 현재 클라이언트의 수
        val messagePayload = mapOf("nickname" to nickname, "message" to message, "type" to "user",  "clients" to clients)
        val messageJSON = jacksonObjectMapper().writeValueAsString(messagePayload)

        for (client in CLIENTS) {
            client.value.basicRemote.sendText(messageJSON)
        }
    }

    @OnError
    @Throws(Exception::class)
    fun onError(session: Session, throwable: Throwable) {
        println("JAVA EE WS API Error")
        println("cause: ${throwable.localizedMessage}")
    }
}
