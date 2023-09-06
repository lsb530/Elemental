package com.example.elemental.constant

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, var message: String) {
    // Common
    INVALID_UUID(HttpStatus.BAD_REQUEST, "유효하지 않은 UUID 값 요청입니다"),
    INVALID_FIELDS(HttpStatus.BAD_REQUEST, "유효하지 않은 데이터 요청입니다"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터 요청입니다"),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터가 비어있습니다"),
    INVALID_JSON(HttpStatus.BAD_REQUEST, "Json 형식이 잘못되었습니다"),
    INVALID_ENUM(HttpStatus.BAD_REQUEST, "Enum 형식이 잘못되었습니다"),

    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "유효시간이 만료된 토큰 요청입니다"),
    INVALID_ACCESS(HttpStatus.FORBIDDEN, "유효하지 않은 접근입니다"),

    // File
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 파일이 존재하지 않습니다"),
    INVALID_FILE_FORMAT(HttpStatus.NOT_FOUND, "알맞은 파일 형식이 아닙니다"),


    // Board
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "대상 board 데이터가 존재하지 않습니다"),

}