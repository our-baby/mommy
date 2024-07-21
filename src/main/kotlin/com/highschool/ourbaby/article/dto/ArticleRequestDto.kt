package com.highschool.ourbaby.article.dto

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity

data class ArticleRequestDto(
	val title: String,
	val summary: String,
	val link: String,
	val categoryId: Long?,
	val hits: Int,
	val linkHits: Int,
	val isPublished: Boolean,
) {
}
