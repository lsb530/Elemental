package com.example.elemental.advice

import com.annotation.backend.exception.CustomException
import com.annotation.backend.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ExceptionHandleRestController {
    fun getErrResponse(ex: CustomException, request: HttpServletRequest):
            ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            title = ex.errorCode.name,
            status = ex.errorCode.status.value(),
            message = ex.message ?: ex.errorCode.message,
            request.requestURI,
            request.method
        )
        return ResponseEntity(errorResponse, ex.errorCode.status)
    }
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(ex: NoHandlerFoundException, request: HttpServletRequest)
            : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            title = "API PATH NOT FOUND",
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
            request.requestURI,
            request.method
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [CustomException::class])
    fun handleCustomException(ex: CustomException, request: HttpServletRequest)
            : ResponseEntity<ErrorResponse> {
        return getErrResponse(ex, request)
    }
    @ExceptionHandler(value = [RuntimeException::class])
    fun handleRuntimeException(ex: RuntimeException, request: HttpServletRequest)
            : ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            title = "Server Error",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = ex.message,
            request.requestURI,
            request.method
        )
//        logger.error { "Server error: ${ex::class.java}\nMessage: ${ex.message}" }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}