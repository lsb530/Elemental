package com.example.elemental.socket

import jakarta.websocket.*
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Service
import java.util.*

@ServerEndpoint(value = "/chat")
@Service
class JavaEEWebSocketService {
    /*
    * Spring Boot 환경에서 Java EE의 @ServerEndpoint 어노테이션을 사용하면,
    * 해당 클래스의 인스턴스는 WebSocket 연결이 생성될 때마다 새로 생성됩니다.
    * 이는 Spring의 일반적인 싱글턴 빈(Like @Component, @Service, @Repository 등)과는 다르게 동작하므로, @Autowired를 이용한 의존성 주입이 동작하지 않습니다.
    * ServerEndpointExporter 빈을 등록하는 것은 Spring Boot 애플리케이션에서 @ServerEndpoint 어노테이션을 사용할 경우 필요한 설정입니다.
    * 이 클래스는 WebSocket 서버 엔드포인트를 자동으로 등록하고 관리해줍니다.
    * 따라서 @ServerEndpoint를 사용할 때는 Spring의 @Autowired를 사용한 의존성 주입이 제대로 동작하지 않을 가능성이 있으므로, 별도로 초기화 작업이 필요할 수 있습니다.
    * 이러한 문제를 해결하기 위해 @ServerEndpoint 클래스 내부에서 Spring의 ApplicationContext에 직접 접근하여 필요한 빈을 가져오는 방법 등이 종종 사용됩니다.
    * */
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

    @OnError
    @Throws(Exception::class)
    fun onError(session: Session, throwable: Throwable) {
        println("JAVA EE WS API Error")
        println("cause: ${throwable.localizedMessage}")
    }
}