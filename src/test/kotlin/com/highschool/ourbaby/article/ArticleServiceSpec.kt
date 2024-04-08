package com.highschool.ourbaby.article

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.service.ArticleService
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@ContextConfiguration(classes = [SpringDataConfig::class])
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(propagation = NOT_SUPPORTED) // flush
class ArticleServiceSpec(
	private val articleRepository: ArticleRepository
): ExpectSpec() {
	private val articleService = ArticleService(articleRepository)

	init {
		context("게시글 생성할 때") {
			val article = Mock.article()
			expect("입력한 정보 그대로 게시글이 생성된다.") {
				val newArticle = articleService.createArticle(article)
				newArticle.id shouldNotBe null
				newArticle.title shouldBe article.title
				newArticle.summary shouldBe article.summary
				newArticle.link shouldBe article.link
				newArticle.hits shouldBe article.hits
				newArticle.menuTag shouldBe article.menuTag
				newArticle.hits shouldBe article.hits
				newArticle.linkHits shouldBe article.linkHits
				newArticle.isPublished shouldBe article.isPublished
				newArticle.createdAt shouldNotBe null
				newArticle.updatedAt shouldBe null
			}
		}
	}
}
