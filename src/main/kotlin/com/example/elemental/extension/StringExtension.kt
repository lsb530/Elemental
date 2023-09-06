package com.example.elemental.extension

import com.annotation.backend.exception.CustomException
import com.example.elemental.constant.ErrorCode
import java.util.*

fun String.removeWhitespace(): String {
    return this.replace("\\s".toRegex(), "")
}

fun String.capitalizeFirstLetter(): String {
    return this.substring(0, 1).uppercase(Locale.getDefault()) + this.substring(1)
}

fun String.toUUID(): UUID {
    return runCatching { UUID.fromString(this) }
        .getOrElse { throw CustomException(ErrorCode.INVALID_UUID) }
}

fun String.containsSpecialCharacters(): Boolean {
    // 정규 표현식을 사용하여 특수 문자가 있는지 확인
    // 이 정규 표현식은 알파벳, 숫자, 공백이 아닌 문자를 찾습니다.
    val regex = Regex("[^A-Za-z0-9 ]")
    return regex.containsMatchIn(this)
}