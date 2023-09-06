package com.example.elemental.mongo.support

import com.example.elemental.mongo.document.Board
import org.bson.types.ObjectId

interface MongoBoardSupport {
    fun findBoardsByAuthor(author: String): List<Board>

    fun findBoardByObjectId(id: ObjectId): Board?
}