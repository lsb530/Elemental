package com.example.elemental.postgre.controller

import com.annotation.backend.exception.CustomException
import com.example.elemental.constant.ErrorCode
import com.example.elemental.extension.containsSpecialCharacters
import com.example.elemental.extension.toUUID
import com.example.elemental.postgre.dto.request.BoardUpdateRequest
import com.example.elemental.postgre.dto.request.CreateBoardRequest
import com.example.elemental.postgre.service.PostgreBoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/postgre/boards")
@RestController
class PostgreBoardController(
    private val boardService: PostgreBoardService,
) {
    @GetMapping
    fun getBoards(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(boardService.getBoards())
    }

    @GetMapping("/{id}")
    fun getBoard(
        @PathVariable id: String,
    ): ResponseEntity<Any> {
        val boardId = id.toUUID()
        return ResponseEntity.ok().body(boardService.getBoardById(boardId))
    }

    @PostMapping
    fun createBoard(
        @RequestBody createBoardRequestDTO: CreateBoardRequest
    ): ResponseEntity<Any> {
        // destructing declaration(순서를 지켜야 한다. 최대 5개의 필드까지, 사용되지 않는 필드는 _로 처리)
        val (_, title) = createBoardRequestDTO
        println("title = ${title}")
        // 1번
        if (title.containsSpecialCharacters()) {
            throw CustomException(ErrorCode.INVALID_FIELDS, "제목은 특수문자가 들어갈 수 없습니다")
        }
        // 2번
        createBoardRequestDTO.validateTitle()
        return ResponseEntity.ok().body(boardService.createBoard(createBoardRequestDTO))
    }

    @PatchMapping("/{id}")
    fun updateBoard(
        @PathVariable id: String,
        @RequestBody updateRequest: BoardUpdateRequest,
    ): ResponseEntity<Any> {
        val boardId = id.toUUID()
        return ResponseEntity.ok().body(boardService.updateBoard(boardId, updateRequest))
    }
}