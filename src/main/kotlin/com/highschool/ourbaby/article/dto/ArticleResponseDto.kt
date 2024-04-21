package com.highschool.ourbaby.article.dto

import java.time.LocalDateTime

data class ArticleResponseDto(
	val id: Long,
	val title: String,
	val summary: String,
	val link: String,
	val menuTag: String,
	val hits: Int,
	val linkHits: Int,
	val isPublished: Boolean,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
)
