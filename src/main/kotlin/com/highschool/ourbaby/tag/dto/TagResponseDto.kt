package com.highschool.ourbaby.tag.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import java.time.LocalDateTime

data class TagResponseDto(
	val id: Long,
	val name: String,
	val articles: List<ArticleResponseDto>,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
)
