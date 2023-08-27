package com.example.elemental.mongo.document

import com.example.elemental.mongo.BaseTimeEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
data class Board(
    @MongoId
    val id: ObjectId,
    val name: String,
    val title: String,
    val content: String,
    val author: String,
) : BaseTimeEntity()