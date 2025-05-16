package com.kkywalk2.features.challenge

import com.kkywalk2.db.MongoDB
import org.litote.kmongo.eq

data class Challenge(
    val id: String,
    val createdBy: String,
    val title: String,
    val type: ChallengeType,
    val beginAt: Long,
    val endAt: Long,
    val members: List<String>,
    val createdAt: Long,
)

enum class ChallengeType {
    STUDY,
    PROJECT,
    WRITE,
}

class ChallengeRepository {
    private val collection = MongoDB.database.getCollection<Challenge>()

    suspend fun saveChallenge(challenge: Challenge) {
        collection.insertOne(challenge)
    }

    suspend fun findChallengesByUserId(id: String): Challenge? {
        return collection.findOne(Challenge::createdBy eq id)
    }
}
