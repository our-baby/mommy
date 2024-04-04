package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.dto.ExceptionResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalControllerAdvice {
	@ExceptionHandler
	fun handleNoSucheElement(ex: NoSuchElementException): ResponseEntity<ExceptionResponseDto> {
		val errorDto = ExceptionResponseDto(
			HttpStatus.NOT_FOUND.value(),
			ex.message,
		)
		return ResponseEntity(errorDto, HttpStatus.NOT_FOUND)
	}

	@ExceptionHandler
	fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ExceptionResponseDto> {
		val errorDto = ExceptionResponseDto(
			HttpStatus.INTERNAL_SERVER_ERROR.value(),
			ex.message,
		)
		return ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR)
	}
}