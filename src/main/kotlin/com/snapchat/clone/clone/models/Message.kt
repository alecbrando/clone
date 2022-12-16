package com.snapchat.clone.clone.models

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "Message")
data class Message(
    val id: String? = null,
    val sender: String? = null,
    val recipient: String? = null,
    val text: String? = null,
    val timestamp: Date? = null
)