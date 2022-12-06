package com.snapchat.clone.clone.controller

import UserService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.snapchat.clone.clone.models.Snap
import com.snapchat.clone.clone.models.User
import com.snapchat.clone.clone.models.Username
import com.snapchat.clone.clone.services.SnapService
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class SnapchatController(
    private val userService: UserService,
    private val snapService: SnapService,
    private val gson: Gson = Gson()
) {
    private val log: Logger = LoggerFactory.getLogger(SnapchatController::class.java)

    @PostMapping("/users")
    fun createUser(@RequestBody user: User?): User {
        return userService.createUser(user!!)
    }

    @GetMapping("/users/{username}")
    fun getUser(@PathVariable username: String?): User {
        return userService.getUser(username)
    }

    @PostMapping("/users/{username}/friends")
    fun addFriend(@PathVariable username: String?, @RequestBody friendUsername: String): User {
        val name = gson.fromJson(friendUsername, Username::class.java)
        log.info("inside of addFriend in Controller $name.friendUsername")
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