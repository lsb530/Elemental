package com.example.elemental.chat

import jakarta.websocket.OnClose
import jakarta.websocket.OnMessage
import jakarta.websocket.OnOpen
import jakarta.websocket.Session
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Service
import java.util.*

@ServerEndpoint(value = "/chat")
@Service
class ChatService {
    private val CLIENTS: MutableSet<Session> = Collections.synchronizedSet(HashSet())

    @OnOpen
    fun onOpen(session: Session) {
        println(session)
        if (CLIENTS.contains(session)) {
            println("Already connected session, ${session.id}")
        } else {
            CLIENTS.add(session)
            println("New session, ${session.id}")
        }
    }

    @OnClose
    @Throws(Exception::class)
    fun onClose(session: Session) {
        CLIENTS.remove(session)
        println("Close session, ${session.id}")
    }

    @OnMessage
    @Throws(Exception::class)
    fun onMessage(message: String, session: Session?) {
        println("Received message $message, from ${session?.id}")
        for (client in CLIENTS) {
            println("Send message $message")
            client.basicRemote.sendText(message)
        }
    }


}