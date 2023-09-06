package com.example.elemental.postgre.dto.request

import com.annotation.backend.exception.CustomException
import com.example.elemental.constant.ErrorCode
import com.example.elemental.extension.containsSpecialCharacters

data class CreateBoardRequest(
    val name: String,
    val title: String,
    val content: String?,
    val author: String,
) {
    fun validateTitle() {
        if (title.containsSpecialCharacters()) {
            throw CustomException(ErrorCode.INVALID_FIELDS, "제목은 특수문자가 들어갈 수 없습니다")
        }
    }
}