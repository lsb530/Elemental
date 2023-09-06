package com.annotation.backend.exception

import com.example.elemental.constant.ErrorCode

data class CustomException(
    val errorCode: ErrorCode,
    override val message: String?
) : RuntimeException(getErrorMessage(errorCode, message)) {
    constructor(errorCode: ErrorCode) : this(errorCode, null)
    companion object {
        private fun getErrorMessage(errorCode: ErrorCode, customMessage: String?): String {
            return customMessage ?: errorCode.message
        }
    }
}