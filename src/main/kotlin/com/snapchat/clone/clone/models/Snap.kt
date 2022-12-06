package com.snapchat.clone.clone.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collation = "Snap")
data class Snap(
    @Id
    val id: String? = null,
    val senderId: String? = null,
    val recipientId: String? = null,
    val imageUrl: String? = null,
    val viewed: Boolean = false,
    val timestamp: Date? = null // other fields, getters, and setters
)