package com.snapchat.clone.clone.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*
import java.util.*


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    private val log = LoggerFactory.getLogger(WebSocketConfig::class.java)
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        log.info("Registering STOMP endpoints")
        registry.addEndpoint("/snap-app")
    }

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        log.info("Configuring message broker")
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }
}

//class ClientHandshakeHandler : DefaultHandshakeHandler() {
//    private val logger: Logger = LoggerFactory.getLogger(ClientHandshakeHandler::class.java)
//    override fun determineUser(req: ServerHttpRequest, weHandler: WebSocketHandler, attributes: Map<String, Any>): Principal? {
//        val randId = UUID.randomUUID().toString()
//        return UserPrincipal(randId)
//    }
//}