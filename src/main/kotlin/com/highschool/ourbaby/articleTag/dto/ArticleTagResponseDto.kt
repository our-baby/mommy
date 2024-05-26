package com.highschool.ourbaby.articleTag.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.tag.dto.TagResponseDto
import java.time.LocalDateTime

data class ArticleTagResponseDto(
	val id: Long,
	val article: ArticleResponseDto,
	val tag: TagResponseDto,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	companion object Factory {
		fun fromEntity(articleTagEntity: ArticleTagEntity) = ArticleTagResponseDto(
			id = articleTagEntity.id,
			article = ArticleResponseDto.fromEntity(articleTagEntity.article),
			tag = TagResponseDto.fromEntity(articleTagEntity.tag),
			createdAt = articleTagEntity.createdAt,
			updatedAt = articleTagEntity.updatedAt,
		)
	}
}
