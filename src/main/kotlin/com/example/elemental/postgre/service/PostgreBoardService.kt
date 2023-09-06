package com.example.elemental.postgre.service

import com.annotation.backend.exception.CustomException
import com.example.elemental.constant.ErrorCode
import com.example.elemental.postgre.dto.request.BoardUpdateRequest
import com.example.elemental.postgre.dto.request.CreateBoardRequest
import com.example.elemental.postgre.dto.response.BoardResponse
import com.example.elemental.postgre.model.Board
import com.example.elemental.postgre.repository.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Transactional(readOnly = true)
@Service
class PostgreBoardService(
    private val boardRepository: BoardRepository,
) {
    private fun mapToDTO(board: Board): BoardResponse {
        return BoardResponse(
            id = board.id,
            name = board.name,
            title = board.title,
            content = board.content,
            author = board.author
        )
    }

    private fun mapToListDTO(boards: List<Board>): List<BoardResponse> {
        return boards.map { mapToDTO(it) }
            .sortedBy { it.author } // kotlin sort
    }

    private fun findLongestBoard(boards: List<Board>): BoardResponse {
        return boards
            .maxByOrNull { it.content.length }
            ?.let {
                mapToDTO(it)
            } ?: throw CustomException(ErrorCode.INVALID_ACCESS)
    }

    private fun validateAndGetBoard(id: UUID): Board {
        return boardRepository.findByIdOrNull(id)
            ?: throw CustomException(ErrorCode.BOARD_NOT_FOUND)
    }

    fun getBoards(): List<Board> {
        return boardRepository.findAll()
    }

    fun getBoardById(id: UUID): Board {
        // 1 java jpa
        val board = boardRepository.findById(id)
            .orElseThrow { CustomException(ErrorCode.BOARD_NOT_FOUND) }
        // 2 kotlin jpa(default extension)
        return boardRepository.findByIdOrNull(id)?.let {
            println(it)
            it
        } ?: throw CustomException(ErrorCode.BOARD_NOT_FOUND)
    }

    @Transactional // 붙여야지 entityManager.merge()
    fun createBoard(createBoardRequest: CreateBoardRequest): Board {
        // 1 constructor
        val boardOne = Board(
            UUID.randomUUID(),
            createBoardRequest.name,
            createBoardRequest.title,
            createBoardRequest.content ?: "",
            createBoardRequest.author,
        )
        // 2 named parameter (순서가 바뀌어도 상관없음 + 빌더를 구현할 필요가 없음)
        println("boardOne = ${boardOne}")
        val boardTwo = Board(
            name = createBoardRequest.name,
            title = createBoardRequest.title,
            content = createBoardRequest.content ?: "",
            author = createBoardRequest.author
        )
        println("boardTwo = ${boardTwo}")
        return boardRepository.save(boardTwo)
    }

    @Transactional
    fun updateBoard(id: UUID, updateBoardRequest: BoardUpdateRequest): BoardResponse {
        val board = validateAndGetBoard(id)

        board.name = updateBoardRequest.name ?: board.name
        board.title = updateBoardRequest.title ?: board.title
        board.content = updateBoardRequest.content ?: board.content
        board.author = updateBoardRequest.author ?: board.author
        board.updatedAt = LocalDateTime.now()

        return mapToDTO(board)
    }

}