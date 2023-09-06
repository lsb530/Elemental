package com.example.elemental.mongo.repository

import com.example.elemental.mongo.document.Board
import com.example.elemental.mongo.support.MongoBoardSupport
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMongoBoardRepository : MongoRepository<Board, ObjectId>, MongoBoardSupport
