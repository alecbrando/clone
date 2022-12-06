package com.snapchat.clone.clone.services

import UserService
import com.snapchat.clone.clone.errors.UserAlreadyExistsException
import com.snapchat.clone.clone.errors.UserNotFoundException
import com.snapchat.clone.clone.models.User

import com.snapchat.clone.clone.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImp(
    private val userRepository: UserRepository
): UserService {
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    override fun createUser(user: User): User {
        val username = user.username
        log.info("This is the $username")
        // validate user input
        if (userRepository.findByUsername(username) != null) {
            throw UserAlreadyExistsException(username)
        }
        // generate user id
        val userWithId = user.copy(id = UUID.randomUUID().toString(), friends = mutableListOf())
        return userRepository.save(userWithId)
    }

    override fun getUser(username: String?): User {
        return userRepository.findByUsername(username) ?: throw UserNotFoundException(username)
    }

    override fun addFriend(username: String?, friendUsername: String?): User {
        log.info("username $username adding $friendUsername")
        val user = getUser(username)
        val friend = getUser(friendUsername)
        if (!user.friends!!.contains(friend.id) && friend.id != null) {
            user.friends.add(friend.id)
            userRepository.save(user)
        }
        return user
    }
}