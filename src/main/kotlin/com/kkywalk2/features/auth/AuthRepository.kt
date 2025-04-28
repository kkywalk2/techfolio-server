package com.kkywalk2.features.auth

import com.kkywalk2.db.MongoDB
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.eq

data class User(
    @BsonId
    val id: String,
    val email: String,
    val createdAt: Long
)

class AuthRepository {
    private val collection = MongoDB.database.getCollection<User>()

    suspend fun saveUser(user: User) {
        collection.insertOne(user)
    }

    suspend fun findUserById(id: String): User? {
        return collection.findOne(User::id eq id)
    }
}
