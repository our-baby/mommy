package com.highschool.ourbaby.articleTag

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.articleTag.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.articleTag.service.ArticleTagService
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.category.persistence.repository.CategoryRepository
import com.highschool.ourbaby.category.service.CategoryService
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
class ArticleTagServiceSpec(
	private val articleRepository: ArticleRepository,
	private val categoryRepository: CategoryRepository,
	private val articleTagRepository: ArticleTagRepository,
) : ExpectSpec() {
	private val articleService = ArticleService(articleRepository)
	private val categoryService = CategoryService(categoryRepository)
	private val articleTagService = ArticleTagService(articleService, categoryService, articleTagRepository)

	init {
		context("게시글에 태그를 추가할 때") {
			val article = createArticle()
			val tag = createCategory()
			createArticleTag(article.id, tag.id)
			expect("게시글 아이디로 조회하면 관련 태그들이 조회된다.") {
				val tagList = articleTagService.getTagsByArticleId(article.id)
				tagList.size shouldBe 1
				tagList[0].id shouldBe tag.id
			}
			expect("태그 아이디로 조회하면 관련 게시글이 조회된다.") {
				val articleList = articleTagService.getArticlesByTagId(tag.id)
				articleList.size shouldBe 1
				articleList[0].id shouldBe article.id
			}
		}
	}

	fun createArticle(): ArticleEntity {
		val article = Mock.article()
		return articleService.createArticle(
			article.title,
			article.summary,
			article.link,
			article.hits,
			article.linkHits,
			article.isPublished,
			article.category
		)
	}

	fun createCategory(): CategoryEntity {
		val category = Mock.category()
		return categoryService.createCategory(category)
	}

	fun createArticleTag(articleId: Long, tagId: Long) = articleTagService.createArticleTag(articleId, tagId)
}
