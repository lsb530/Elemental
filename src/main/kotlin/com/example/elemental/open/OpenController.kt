package com.example.elemental.open

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/open")
@RestController
class OpenController {
    @GetMapping
    fun health(): ResponseEntity<Any> {
        return ResponseEntity.ok().body("This server is working")
    }
}