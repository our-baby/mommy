package com.highschool.ourbaby.tag.dto

import java.time.LocalDateTime

data class TagResponseDto(
	val id: Long,
	val name: String,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
)
