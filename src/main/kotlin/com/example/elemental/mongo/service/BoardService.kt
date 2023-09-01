package com.example.elemental.mongo.service

import com.example.elemental.mongo.dto.request.CreateBoard
import com.example.elemental.mongo.dto.response.BoardResponse
import com.example.elemental.mongo.mapper.BoardMapper
import com.example.elemental.mongo.repository.BoardRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {
    private val boardMapper = BoardMapper.INSTANCE
    fun getBoards(author: String?): List<BoardResponse> {
        author?.let {
            val boards = boardRepository.findBoardsByAuthor(author)
            return boards.map { boardMapper.convertDocumentToResponse(it) }
        }
        return boardRepository.findAll().map { boardMapper.convertDocumentToResponse(it) }
    }

    fun getBoardWithMongoTemplateById(id: ObjectId): BoardResponse {
        val board = boardRepository.findBoardByObjectId(id)
        return board?.let { boardMapper.convertDocumentToResponse(board) }
            ?: throw RuntimeException("Board not found")
    }

    fun getBoardWithDataRepositoryById(id: ObjectId): BoardResponse {
        return boardRepository.findByIdOrNull(id)?.let {
            boardMapper.convertDocumentToResponse(it)
        } ?: throw RuntimeException("Board not found")
    }

    fun saveBoard(createBoard: CreateBoard): BoardResponse {
        val board = boardMapper.createReqToDocument(createBoard)
        return boardMapper.convertDocumentToResponse(boardRepository.save(board))
    }
}
