package com.highschool.ourbaby.article.dto

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity

data class ArticleRequestDto(
	val title: String,
	val summary: String,
	val link: String,
	val menuTag: String,
	val hits: Int,
	val linkHits: Int,
	val isPublished: Boolean,
) {
	fun toEntity() = ArticleEntity(
		title = this.title,
		summary = this.summary,
		link = this.link,
		menuTag = this.menuTag,
		hits = this.hits,
		linkHits = this.linkHits,
		isPublished = this.isPublished,
	)
}
