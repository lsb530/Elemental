package com.example.elemental.mongo.service

import com.annotation.backend.exception.CustomException
import com.example.elemental.constant.ErrorCode
import com.example.elemental.mongo.dto.request.CreateBoardRequest
import com.example.elemental.mongo.dto.request.UpdateBoardRequest
import com.example.elemental.mongo.dto.response.BoardResponse
import com.example.elemental.mongo.mapper.MongoBoardMapper
import com.example.elemental.mongo.repository.MongoMongoBoardRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MongoBoardService(
    private val mongoBoardRepository: MongoMongoBoardRepository,
) {
    private val boardMapper = MongoBoardMapper.INSTANCE
    fun getBoards(author: String?): List<BoardResponse> {
        author?.let {
            val boards = mongoBoardRepository.findBoardsByAuthor(author)
            return boards.map { boardMapper.convertDocumentToResponse(it) }
        }
        return mongoBoardRepository.findAll().map { boardMapper.convertDocumentToResponse(it) }
    }

    fun getBoardWithMongoTemplateById(id: ObjectId): BoardResponse {
        val board = mongoBoardRepository.findBoardByObjectId(id)
        return board?.let { boardMapper.convertDocumentToResponse(board) }
            ?: throw CustomException(ErrorCode.BOARD_NOT_FOUND)
    }

    fun getBoardWithDataRepositoryById(id: ObjectId): BoardResponse {
        return mongoBoardRepository.findByIdOrNull(id)
            ?.let {
                boardMapper.convertDocumentToResponse(it)
            } ?: throw CustomException(ErrorCode.BOARD_NOT_FOUND)
    }

    fun createBoard(createBoardRequest: CreateBoardRequest): BoardResponse {
        val board = boardMapper.createReqToDocument(createBoardRequest)
        return boardMapper.convertDocumentToResponse(mongoBoardRepository.save(board))
    }

    fun updateBoard(updateBoardRequest: UpdateBoardRequest): BoardResponse {
        val (requestId) = updateBoardRequest
        requestId?.let {
            val board = mongoBoardRepository.findBoardByObjectId(requestId)
            board?.let {
                val newBoard = board.copy(
                    name = updateBoardRequest.name ?: it.name,
                    title = updateBoardRequest.title ?: it.title,
                    content = updateBoardRequest.content ?: it.content,
                    author = updateBoardRequest.author ?: it.author,
                )
                val savedBoard = mongoBoardRepository.save(newBoard)
                return boardMapper.convertDocumentToResponse(savedBoard)
            }
        } ?: throw CustomException(ErrorCode.MISSING_PARAMETER, "ID가 비어있습니다")
    }
}
