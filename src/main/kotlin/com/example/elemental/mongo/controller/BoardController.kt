package com.example.elemental.mongo.controller

import com.example.elemental.mongo.dto.request.CreateBoard
import com.example.elemental.mongo.service.BoardService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/mongo/boards")
@RestController
class BoardController(
    private val boardService: BoardService,
) {
    fun validObjectId(id: String): ObjectId {
        try {
            ObjectId.isValid(id)
            return ObjectId(id)
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }

    @GetMapping
    fun getBoards(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(boardService.getBoards())
    }

    @GetMapping("/{id}")
    fun getBoard(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        val objectId = validObjectId(id)
        return ResponseEntity.ok().body(boardService.getBoard(objectId))
    }

    @PostMapping
    fun createBoard(@RequestBody createBoardDTO: CreateBoard): ResponseEntity<Any> {
        return ResponseEntity.ok().body(boardService.saveBoard(createBoardDTO))
    }

}