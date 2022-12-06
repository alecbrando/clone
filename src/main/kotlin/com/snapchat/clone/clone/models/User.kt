package com.snapchat.clone.clone.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "User")
data class User(
    @Id
    val id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val friends: MutableList<String>? = null // other fields, getters, and setters
)