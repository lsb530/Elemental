package com.example.elemental.mongo.repository

import com.example.elemental.mongo.document.Board
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : MongoRepository<Board, ObjectId>
