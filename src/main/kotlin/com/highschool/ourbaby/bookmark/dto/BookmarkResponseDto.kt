package com.highschool.ourbaby.bookmark.dto

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import com.highschool.ourbaby.member.dto.MemberResponseDto

data class BookmarkResponseDto(
	val id: Long,
	val article: ArticleResponseDto,
	val member: MemberResponseDto,
) {
	constructor (bookmarkEntity: BookmarkEntity) : this(
		bookmarkEntity.id,
		ArticleResponseDto(bookmarkEntity.article),
		MemberResponseDto(bookmarkEntity.member),
	) {
	}
}
