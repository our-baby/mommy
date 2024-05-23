package com.highschool.ourbaby.bookmark.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity

data class BookmarkResponseDto(
	val id: Long,
	val article: ArticleResponseDto,
	val member: Long, // TODO: Change to MemberResponseDto
) {
	companion object Factory {
		fun fromEntity(bookmarkEntity: BookmarkEntity) = BookmarkResponseDto(
			id = bookmarkEntity.id,
			article = ArticleResponseDto.fromEntity(bookmarkEntity.article),
			member = bookmarkEntity.member, // TODO: MemberResponseDto.fromEntity(bookmarkEntity.member)
		)
	}
}
