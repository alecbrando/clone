package com.snapchat.clone.clone.repository

import com.snapchat.clone.clone.models.Message
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : MongoRepository<Message?, String?> {
    fun findBySenderAndRecipient(sender: String?, recipient: String?): List<Message?>?
}