package com.example.elemental.mongo.controller

import com.example.elemental.mongo.dto.request.CreateBoardRequest
import com.example.elemental.mongo.dto.request.UpdateBoardRequest
import com.example.elemental.mongo.service.MongoBoardService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/mongo/boards")
@RestController
class MongoBoardController(
    private val mongoBoardService: MongoBoardService,
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
    fun getBoards(
        @RequestParam author: String?,
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(mongoBoardService.getBoards(author))
    }

    @GetMapping("/{id}")
    fun getBoard(
        @PathVariable id: String,
        @RequestParam(defaultValue = "false") useMongoTemplate: Boolean?
    ): ResponseEntity<Any> {
        val objectId = validObjectId(id)
        if (useMongoTemplate == true) {
            return ResponseEntity.ok().body(mongoBoardService.getBoardWithMongoTemplateById(objectId))
        }
        return ResponseEntity.ok().body(mongoBoardService.getBoardWithDataRepositoryById(objectId))
    }

    @PostMapping
    fun createBoard(@RequestBody createBoardRequestDTO: CreateBoardRequest): ResponseEntity<Any> {
        return ResponseEntity.ok().body(mongoBoardService.createBoard(createBoardRequestDTO))
    }

    @PatchMapping("/{id}")
    fun updateBoard(
        @PathVariable id: String,
        @RequestBody updateBoardRequest: UpdateBoardRequest)
    : ResponseEntity<Any> {
        val updateBoardResponse = mongoBoardService.updateBoard(
            updateBoardRequest.copy(id = validObjectId(id))
        )
        return ResponseEntity.ok().body(updateBoardResponse)
    }

}