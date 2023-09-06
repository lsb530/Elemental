package com.example.elemental.postgre.dto.response

import java.util.*

data class BoardResponse(
    val id: UUID,
    val name: String,
    val title: String,
    val content: String,
    val author: String,
)
