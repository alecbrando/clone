package com.snapchat.clone.clone.controller

import UserService
import com.google.gson.Gson
import com.snapchat.clone.clone.models.*
import com.snapchat.clone.clone.repository.MessageRepository
import com.snapchat.clone.clone.services.SnapService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.util.*


@RestController
class SnapchatController(
        private val userService: UserService,
        private val snapService: SnapService,
        private val gson: Gson = Gson()
) {
    private val log: Logger = LoggerFactory.getLogger(SnapchatController::class.java)

    @Autowired
    private lateinit var simpMessagingTemplate: SimpMessagingTemplate

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleAlreadyExists(e: IllegalArgumentException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @MessageMapping("/messages")
    fun sendMessageToUser(message: Message): Message {
        log.info("Hello World")
        // Create a new message object with the incoming message data
        val messageToSave = Message(UUID.randomUUID().toString(), message.sender, message.recipient, message.text, timestamp = Date())
        // Query the MongoDB collection for messages with the same sender and recipient
        messageRepository.save(messageToSave)
        simpMessagingTemplate.convertAndSendToUser(message.sender ?: "", "/messages/${message.recipient}", message)
        return messageToSave
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): TokenResponse {
        return userService.createUser(user)
    }

    @GetMapping("/users/{username}")
    fun getUser(@PathVariable username: String?): User {
        return userService.getUser(username)
    }

    @PostMapping("/users/{username}/friends")
    fun addFriend(@PathVariable username: String?, @RequestBody friendUsername: String): User {
        val name = gson.fromJson(friendUsername, Username::class.java)
        log.info("inside of addFriend in Controller ${name.friendUsername}")
        return userService.addFriend(username, name.friendUsername)
    }

    @PostMapping("/snaps")
    fun sendSnap(@RequestBody snap: Snap?): Snap {
        return snapService.sendSnap(snap!!)
    }

    @GetMapping("/snaps/{recipientId}")
    fun getSnaps(@PathVariable recipientId: String?): List<Snap?>? {
        return snapService.getSnaps(recipientId)
    }
}

