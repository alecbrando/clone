package com.snapchat.clone.clone

import UserService
import com.snapchat.clone.clone.models.User
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication()
@EnableMongoRepositories
class CloneApplication {
//	@Bean
//	fun run(userService: UserService): CommandLineRunner {
//		return CommandLineRunner { args ->
//			userService.createUser(User(username ="dantheman", password = "random", email = "test2@test.com", phone = "480120312"))
//		}
//	}
}

fun main(args: Array<String>) {
	runApplication<CloneApplication>(*args)
}

//68.82.54.3/32
//snapchatdatabase
//Ty1HU6ttxDBMVooz