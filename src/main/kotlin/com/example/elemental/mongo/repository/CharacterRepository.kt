package com.example.elemental.mongo.repository
import com.example.elemental.mongo.model.Character
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CharacterRepository : MongoRepository<Character, ObjectId>
