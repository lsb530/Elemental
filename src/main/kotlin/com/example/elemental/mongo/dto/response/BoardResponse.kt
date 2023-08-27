package com.example.elemental.mongo.dto.response

data class BoardResponse(
    val id: String,
    val name: String,
    val title: String,
    val content: String,
    val author: String,
)