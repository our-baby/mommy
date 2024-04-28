package com.highschool.ourbaby.article.dto

import com.highschool.ourbaby.tag.dto.TagResponseDto
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
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
	var tagList: List<TagResponseDto> = ArrayList<TagResponseDto>(),
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	companion object Factory {
		fun fromEntity(articleEntity: ArticleEntity) = ArticleResponseDto(
			id = articleEntity.id,
			title = articleEntity.title,
			summary = articleEntity.summary,
			link = articleEntity.link,
			menuTag = articleEntity.menuTag,
			hits = articleEntity.hits,
			linkHits = articleEntity.linkHits,
			isPublished = articleEntity.isPublished,
			createdAt = articleEntity.createdAt,
			updatedAt = articleEntity.updatedAt,
		)
	}

}
