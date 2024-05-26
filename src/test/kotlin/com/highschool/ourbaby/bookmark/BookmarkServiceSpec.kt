package com.highschool.ourbaby.bookmark

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.bookmark.persistence.repository.BookmarkRepository
import com.highschool.ourbaby.bookmark.service.BookmarkService
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.ContextConfiguration

@EnableJpaAuditing
@DataJpaTest
@ContextConfiguration(classes = [SpringDataConfig::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookmarkServiceSpec(
	private val articleRepository: ArticleRepository,
	private val bookmarkRepository: BookmarkRepository,
) : ExpectSpec() {
	private val articleService = ArticleService(articleRepository)
	private val bookmarkService = BookmarkService(articleService, bookmarkRepository)

	init {
		context("북마크 생성할 때") {
			expect("멤버 아이디로 조회하면 북마크 게시글이 조회된다.") {
				val article = createArticle()
				val memberId: Long = 1
				val bookmark = createBookmark(article, memberId)
				val articleList = bookmarkService.getArticlesByMemberId(memberId)
				articleList.size shouldBe 1
				articleList[0].id shouldBe article.id
			}
		}
	}

	fun createArticle(): ArticleEntity {
		val article = Mock.article()
		return articleService.createArticle(article)
	}

	fun createBookmark(article: ArticleEntity, memberId: Long) = bookmarkService.createBookmark(article.id, memberId)


}