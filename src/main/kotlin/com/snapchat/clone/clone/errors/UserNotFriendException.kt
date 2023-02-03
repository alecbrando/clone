package com.snapchat.clone.clone.errors

import org.springframework.security.acls.model.NotFoundException

open class UserNotFriendException(val username: String): NotFoundException("User $username is not friends with you")