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
    fun getBoards(): List<BoardResponse> {
        return boardRepository.findAll().map { boardMapper.convertToDto(it) }
    }

    fun getBoard(id: ObjectId): BoardResponse {
        return boardRepository.findByIdOrNull(id)?.let {
            boardMapper.convertToDto(it)
        } ?: throw RuntimeException("Board not found")
    }

    fun saveBoard(boardReq: CreateBoard): BoardResponse {
        val board = boardMapper.convertToModel(boardReq)
        return boardMapper.convertToDto(boardRepository.save(board))
    }
}
