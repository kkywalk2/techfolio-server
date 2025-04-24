package com.kkywalk2.db

import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo

object MongoDB {
    private val client = KMongo.createClient(System.getenv("MONGO_URI") ?: "mongodb://techfolio_user:secure_password123@localhost:27017/techfolio?authSource=admin").coroutine
    val database = client.getDatabase("techfolio")
}
