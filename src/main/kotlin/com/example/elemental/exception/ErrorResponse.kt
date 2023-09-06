package com.annotation.backend.exception

import java.time.LocalDateTime

data class ErrorResponse(
    var title: String,
    var status: Int,
    var message: String?,
    var path: String?,
    var method: String?,
    var timestamp: LocalDateTime = LocalDateTime.now(),
)