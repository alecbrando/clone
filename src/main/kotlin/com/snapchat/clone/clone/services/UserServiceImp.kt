package com.snapchat.clone.clone.services

import UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.snapchat.clone.clone.models.TokenResponse
import com.snapchat.clone.clone.models.User
import com.snapchat.clone.clone.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImp(
    private val userRepository: UserRepository
): UserService {
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    override fun createUser(user: User): TokenResponse {
        val username = user.username
        log.info("This is the $username")
        // validate phone number
        val phoneNumberRegex = Regex(pattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}\$")
        if (user.phone?.matches(phoneNumberRegex) != true) {
            throw IllegalArgumentException( "Invalid Phone Number ${user.phone}")
        }
        // validate user input
        if (userRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("$username is already taken")
        }
        if (userRepository.findByPhone(user.phone) != null) {
            throw IllegalArgumentException("${user.phone} is already in use")
        }
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val userWithId = user.copy(id = UUID.randomUUID().toString(), friends = mutableListOf(), password = hashedPassword)
        val savedUser = userRepository.save(userWithId)
        val algorithm = Algorithm.HMAC512("yoursecret".toByteArray());
        val jwt = JWT.create()
                .withSubject(user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);
        return TokenResponse(
                savedUser.id!!,
                savedUser.username!!,
                jwt
        )
    }

    override fun getUser(username: String?): User {
        return userRepository.findByUsername(username) ?: throw IllegalArgumentException("No User Found")
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