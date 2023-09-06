package com.example.elemental.mongo.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId

data class UpdateBoardRequest(
    @JsonIgnore
    val id: ObjectId?,
    val name: String?,
    val title: String?,
    val content: String?,
    val author: String?,
)