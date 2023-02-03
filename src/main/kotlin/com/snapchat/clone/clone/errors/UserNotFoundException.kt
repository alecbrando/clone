package com.snapchat.clone.clone.errors

import org.springframework.security.acls.model.NotFoundException

open class UserNotFoundException(val username: String?):
    NotFoundException(String.format("User %s not found", username))