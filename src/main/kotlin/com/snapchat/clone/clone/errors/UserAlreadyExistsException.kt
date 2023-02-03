package com.snapchat.clone.clone.errors

import org.springframework.security.acls.model.AlreadyExistsException

open class UserAlreadyExistsException(val username: String?) :
    AlreadyExistsException(String.format("User %s already exists", username))