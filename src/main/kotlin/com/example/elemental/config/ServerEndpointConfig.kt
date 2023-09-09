package com.example.elemental.config

import jakarta.websocket.server.ServerEndpointConfig
import org.springframework.beans.factory.BeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Configuration
import kotlin.concurrent.Volatile

@Configuration
class ServerEndpointConfig: ServerEndpointConfig.Configurator(), ApplicationContextAware {
    companion object {
        @Volatile // 멀티스레딩 환경에서 context가 항상 메모리에서 읽히도록 보장
        private var context: BeanFactory? = null
    }

    override fun <T : Any> getEndpointInstance(clazz: Class<T>): T {
        return context!!.getBean(clazz)
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }
}