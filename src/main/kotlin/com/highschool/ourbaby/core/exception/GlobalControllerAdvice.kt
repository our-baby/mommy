package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.dto.ExceptionResponseDto
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler
    fun handleBadRequest(ex: BadRequestException): ResponseEntity<ExceptionResponseDto> {
        val errorDto =
            ExceptionResponseDto(
                false,
                ex.message,
            )
        return ResponseEntity(errorDto, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleNoSuchElement(ex: NoSuchElementException): ResponseEntity<ExceptionResponseDto> {
        val errorDto =
            ExceptionResponseDto(
                false,
                ex.message,
            )
        return ResponseEntity(errorDto, NOT_FOUND)
    }

    @ExceptionHandler
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<ExceptionResponseDto> {
        val errorDto =
            ExceptionResponseDto(
                false,
                ex.message,
            )
        return ResponseEntity(errorDto, BAD_REQUEST)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleRuntimeException(ex: Exception): ResponseEntity<ExceptionResponseDto> {
        val errorDto =
            ExceptionResponseDto(
                false,
                ex.message,
            )
        return ResponseEntity(errorDto, INTERNAL_SERVER_ERROR)
    }
}
