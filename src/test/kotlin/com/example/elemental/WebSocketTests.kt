//package com.example.elemental
//
//import com.example.elemental.chat.Greeting
//import com.example.elemental.chat.HelloMessage
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.test.web.server.LocalServerPort
//import org.springframework.messaging.simp.stomp.StompSession
//import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
//import org.springframework.util.LinkedMultiValueMap
//import org.springframework.web.socket.WebSocketHttpHeaders
//import org.springframework.web.socket.messaging.WebSocketStompClient
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class WebSocketTests(@Autowired val restTemplate: TestRestTemplate) {
//
//    @Autowired
//    lateinit var webSocketStompClient: WebSocketStompClient
//
//    @LocalServerPort
//    private val port: Int = 0
//
//    lateinit var stompSession: StompSession
//
//    @BeforeEach
//    fun setup() {
//        val wsUrl = "ws://localhost:$port/gs-guide-websocket"
//        val headers = LinkedMultiValueMap<String, String>()
//        val sessionHandler = object : StompSessionHandlerAdapter() {}
//
//        stompSession = webSocketStompClient
//            .connect(wsUrl, WebSocketHttpHeaders(headers), sessionHandler)
//            .get(5, TimeUnit.SECONDS)
//    }
//
//    @Test
//    fun `Verify greeting message`() {
//        val latch = CountDownLatch(1)
//        stompSession.subscribe("/topic/greeting") { frame ->
//            val greeting = objectMapper.readValue(frame.payload, Greeting::class.java)
//            assertEquals("Hello John", greeting.content)
//            latch.countDown()
//        }
//        stompSession.send("/app/hello", HelloMessage("John"))
//        assertTrue(latch.await(10, TimeUnit.SECONDS))
//    }
//}
