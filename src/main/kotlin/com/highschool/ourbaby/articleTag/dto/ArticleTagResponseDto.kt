package com.highschool.ourbaby.articleTag.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.category.dto.CategoryResponseDto

data class ArticleTagResponseDto(
	val id: Long,
	val article: ArticleResponseDto,
	val tag: CategoryResponseDto,
) {
	constructor(articleTagEntity: ArticleTagEntity): this(
		articleTagEntity.id,
		ArticleResponseDto(articleTagEntity.article),
		CategoryResponseDto(articleTagEntity.tag),
	)
}
