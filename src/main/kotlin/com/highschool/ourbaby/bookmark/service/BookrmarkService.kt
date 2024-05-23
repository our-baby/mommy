package com.highschool.ourbaby.bookmark.service

import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import com.highschool.ourbaby.bookmark.persistence.repository.BookmarkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookmarkService(
	private val articleService: ArticleService,
	private val bookmarkRepository: BookmarkRepository
) {

	fun getAllBookmark(): List<BookmarkEntity> = bookmarkRepository.findAll()

	@Transactional
	fun createBookmark(articleId: Long, memberId: Long): BookmarkEntity {
		val article = articleService.getArticleById(articleId)
		// TODO: get member entity from memberService
		return bookmarkRepository.save(BookmarkEntity(article = article, member = memberId))
	}

	@Transactional
	fun deleteBookmark(articleId: Long, memberId: Long) {
		bookmarkRepository.deleteByArticleIdAndMember(articleId, memberId)
	}

}