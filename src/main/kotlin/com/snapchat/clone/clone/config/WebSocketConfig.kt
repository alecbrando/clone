package com.snapchat.clone.clone.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.HandshakeInterceptor


//import org.springframework.context.annotation.Configuration
//import org.springframework.messaging.simp.config.MessageBrokerRegistry
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/my-websocket-endpoint")
            .addInterceptors(MyWebSocketHandshakeInterceptor())
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
    }
}

class MyWebSocketHandshakeInterceptor : HandshakeInterceptor {

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: java.lang.Exception?
    ) {

    }
}

class MyMessageHandler : MessageHandler {
    private val log: Logger = LoggerFactory.getLogger(MyMessageHandler::class.java)

    override fun handleMessage(message: Message<*>) {
        log.info("this is the message: ${message.payload}")
    }
}