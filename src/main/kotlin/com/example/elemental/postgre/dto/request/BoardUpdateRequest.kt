package com.example.elemental.postgre.dto.request

data class BoardUpdateRequest(
    val name: String?,
    val title: String?,
    val content: String?,
    val author: String?,
)
