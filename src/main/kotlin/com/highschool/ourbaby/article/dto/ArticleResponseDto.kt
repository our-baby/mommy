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
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
	var tagList: List<TagResponseDto> = ArrayList<TagResponseDto>(),
) {
	constructor (articleEntity: ArticleEntity) : this(
		articleEntity.id,
		articleEntity.title,
		articleEntity.summary,
		articleEntity.link,
		articleEntity.menuTag,
		articleEntity.hits,
		articleEntity.linkHits,
		articleEntity.isPublished,
		articleEntity.createdAt,
		articleEntity.updatedAt
	) {
	}


}
