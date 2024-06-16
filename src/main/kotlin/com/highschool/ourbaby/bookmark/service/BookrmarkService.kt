package com.highschool.ourbaby.bookmark.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import com.highschool.ourbaby.bookmark.persistence.repository.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class BookmarkService(
	private val articleService: ArticleService,
	private val bookmarkRepository: BookmarkRepository
) {

	fun getAllBookmark(): List<BookmarkEntity> = bookmarkRepository.findAll()

	fun getArticlesByMemberId(id: Long): List<ArticleEntity> =
		bookmarkRepository.findArticlesByMemberId(id).map { it -> it.article }


	fun createBookmark(articleId: Long, memberId: Long): BookmarkEntity {
		val article = articleService.getArticleById(articleId)
		// TODO: get member entity from memberService
		return bookmarkRepository.save(BookmarkEntity(article = article, member = memberId))
	}

	fun deleteBookmark(articleId: Long, memberId: Long) =
		bookmarkRepository.deleteByArticleIdAndMember(articleId, memberId)


}
