package com.example.elemental.mongo.dto.request

data class CreateBoard(
    val name: String,
    val title: String,
    val content: String,
    val author: String,
)