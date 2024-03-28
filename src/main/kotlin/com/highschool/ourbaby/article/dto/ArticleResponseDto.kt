package com.highschool.ourbaby.article.dto

data class ArticleResponseDto(
	val id: Long?,
	val title: String,
	val summary: String,
	val link: String,
	val menuTag: String,
	val hits: Int,
	val linkHits: Int,
	val isPublished: Boolean,
)