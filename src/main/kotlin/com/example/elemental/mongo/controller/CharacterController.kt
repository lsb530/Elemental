package com.example.elemental.mongo.controller

import com.example.elemental.mongo.dto.CreateCharacterDTO
import com.example.elemental.mongo.service.CharacterService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/mongo/characters")
@RestController
class CharacterController(
    private val characterService: CharacterService
) {
    fun validObjectId(id: String): ObjectId {
        try {
            ObjectId.isValid(id)
            return ObjectId(id)
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }
    @PostMapping
    fun createCharacter(@RequestBody createCharacterDTO: CreateCharacterDTO): ResponseEntity<Any> {
        return ResponseEntity.ok().body(characterService.saveCharacter(createCharacterDTO))
    }

    @GetMapping
    fun getCharacters(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(characterService.getAllCharacters())
    }

    @GetMapping("/{id}")
    fun getCharacter(@PathVariable id: String): ResponseEntity<Any> {
        return ResponseEntity.ok().body(characterService.getCharacterById(validObjectId(id)))
    }
}