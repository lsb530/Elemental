package com.example.elemental.mongo.support

import com.example.elemental.mongo.document.Board
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class BoardSupportImpl(
    private val mongoTemplate: MongoTemplate,
) : BoardSupport {
    override fun findBoardsByAuthor(author: String): MutableList<Board> {
        val criteria = Criteria.where("author").`is`(author)
        val query = Query(criteria)

        val boards = mongoTemplate.find(query, Board::class.java, "board")
        return boards
    }

    override fun findBoardByObjectId(id: ObjectId): Board? {
        val criteria = Criteria.where("_id").`is`(id)
        val query = Query(criteria)
        val findBoard = mongoTemplate.findOne(query, Board::class.java, "board")
        return findBoard
    }
}