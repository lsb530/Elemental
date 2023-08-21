package com.example.elemental.mongo.model

import com.example.elemental.mongo.BaseTimeEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "characters")
class Character(
    @MongoId val id: ObjectId,
    val name: String,
    val age: Int
) : BaseTimeEntity()