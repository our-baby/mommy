package com.highschool.ourbaby.article.dto

import com.highschool.ourbaby.category.dto.CategoryResponseDto
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import java.time.LocalDateTime

data class ArticleResponseDto(
	val id: Long,
	val title: String,
	val summary: String,
	val link: String,
	val hits: Int,
	val linkHits: Int,
	val isPublished: Boolean,
	val category: CategoryResponseDto,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	constructor (articleEntity: ArticleEntity) : this(
		articleEntity.id,
		articleEntity.title,
		articleEntity.summary,
		articleEntity.link,
		articleEntity.hits,
		articleEntity.linkHits,
		articleEntity.isPublished,
		CategoryResponseDto(articleEntity.category),
		articleEntity.createdAt,
		articleEntity.updatedAt,
	) {
	}
}
