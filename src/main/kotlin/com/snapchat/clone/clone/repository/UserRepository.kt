package com.snapchat.clone.clone.repository

import com.snapchat.clone.clone.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User?, String?> {
    fun findByUsername(username: String?): User?
    fun findByEmail(email: String?): User?
    fun findByPhone(phone: String?): User?
}