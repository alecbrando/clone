package com.snapchat.clone.clone.repository

import com.snapchat.clone.clone.models.Snap

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SnapRepository : MongoRepository<Snap?, String?> {
    fun findBySenderId(senderId: String?): List<Snap?>?
    fun findByRecipientId(recipientId: String?): List<Snap?>?
    fun findBySenderIdAndRecipientId(senderId: String?, recipientId: String?): List<Snap?>?
    fun findByTimestampBeforeAndRecipientId(timestamp: Date?, recipientId: String?): List<Snap?>?
}