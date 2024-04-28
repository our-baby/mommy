package com.highschool.ourbaby.article.dto

import com.highschool.ourbaby.tag.dto.TagResponseDto
import java.time.LocalDateTime

data class ArticleTagResponseDto (
	val id: Long,
	val article: ArticleResponseDto,
	val tag: TagResponseDto,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
)
