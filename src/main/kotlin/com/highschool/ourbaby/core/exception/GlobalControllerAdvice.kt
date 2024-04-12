package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.dto.ExceptionResponseDto
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
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
	fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ExceptionResponseDto> {
		val errorDto = ExceptionResponseDto(
			INTERNAL_SERVER_ERROR.value(),
			ex.message,
		)
		return ResponseEntity(errorDto, INTERNAL_SERVER_ERROR)
	}
}
