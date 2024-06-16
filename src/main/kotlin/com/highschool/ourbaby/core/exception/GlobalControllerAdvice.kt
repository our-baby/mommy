package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.dto.ExceptionResponseDto
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler
    fun handleNoSuchElement(ex: NoSuchElementException): ResponseEntity<ExceptionResponseDto> {
        val errorDto = ExceptionResponseDto(
            NOT_FOUND.value(),
            ex.message,
        )
        return ResponseEntity(errorDto, NOT_FOUND)
    }

    @ExceptionHandler
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<ExceptionResponseDto> {
        val errorDto = ExceptionResponseDto(
            BAD_REQUEST.value(),
            ex.message,
        )
        return ResponseEntity(errorDto, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ExceptionResponseDto> {
        val errorDto = ExceptionResponseDto(
            INTERNAL_SERVER_ERROR.value(),
            ex.message,
        )
        return ResponseEntity(errorDto, INTERNAL_SERVER_ERROR)
    }
}
