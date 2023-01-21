package com.snapchat.clone.clone

import UserService
import com.snapchat.clone.clone.models.Message
import com.snapchat.clone.clone.models.User
import com.snapchat.clone.clone.repository.MessageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.socket.config.annotation.EnableWebSocket
import java.util.*


@SpringBootApplication()
@EnableMongoRepositories
@EnableWebSocket
class CloneApplication {
//	@Bean
//	fun run(messageRepository: MessageRepository): CommandLineRunner {
//		return CommandLineRunner { args ->
////			userService.createUser(User(username ="dantheman", password = "random", email = "test2@test.com", phone = "480120312"))
//			val messageToSave = Message(UUID.randomUUID().toString(), "alecbrando", "dantheman", "Hello how are you?", timestamp = Date())
//			messageRepository.save(messageToSave)
//		}
//	}
}

fun main(args: Array<String>) {
	runApplication<CloneApplication>(*args)
}

//68.82.54.3/32
//snapchatdatabase
//Ty1HU6ttxDBMVooz