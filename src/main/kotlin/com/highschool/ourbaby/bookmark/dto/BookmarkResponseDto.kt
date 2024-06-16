package com.highschool.ourbaby.bookmark.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity

data class BookmarkResponseDto(
	val id: Long,
	val article: ArticleResponseDto,
	val member: Long, // TODO: Change to MemberResponseDto
) {
	constructor (bookmarkEntity: BookmarkEntity) : this(
		bookmarkEntity.id,
		ArticleResponseDto(bookmarkEntity.article),
		bookmarkEntity.member,
	) {
	}
}
