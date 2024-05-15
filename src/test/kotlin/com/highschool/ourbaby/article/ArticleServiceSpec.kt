package com.highschool.ourbaby.article

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.persistence.repository.TagRepository
import com.highschool.ourbaby.tag.service.TagService
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.ContextConfiguration

@EnableJpaAuditing
@DataJpaTest
@ContextConfiguration(classes = [SpringDataConfig::class])
@AutoConfigureTestDatabase(replace = NONE)
class ArticleServiceSpec(
	private val articleRepository: ArticleRepository,
	private val articleTagRepository: ArticleTagRepository,
	private val tagRepository: TagRepository,
) : ExpectSpec() {
	private val tagService = TagService(tagRepository)
	private val articleService = ArticleService(articleRepository, tagService, articleTagRepository)

	init {
		context("게시글 생성할 때") {
			expect("입력한 정보 그대로 게시글이 생성된다.") {
				val article = Mock.article()
				val newArticle = createNewArticle(article)
				validate(newArticle, article)
			}
		}
		context("게시글 수정할 때") {
			expect("입력한 정보 그대로 게시글이 수정된다.") {
				val article = Mock.article()
				var newArticle = createNewArticle(article)
				val updatedArticle = Mock.article()
				newArticle = articleService.updateArticle(newArticle.id, updatedArticle)
				validate(newArticle, updatedArticle)
			}
		}

		context("게시글 조회할 때") {
			expect("게시글 생성한 후 전체 조회할 때 똑같이 보여진다.") {
				val article = Mock.article()
				createNewArticle(article)
				val articles = articleService.getAllArticles()
				articles.size shouldBeGreaterThan 0
			}
		}

		context("특정 게시글 조회할 때") {
			expect("생성한 게시글이 똑같이 보여진다.") {
				val article = Mock.article()
				val newArticle = createNewArticle(article)
				val viewed = articleService.getArticleById(newArticle.id)
				validate(viewed, newArticle)
			}
		}

		context("게시글 삭제할 때") {
			expect("원하는 게시글이 정상적으로 삭제된다.") {
				val article = Mock.article()
				val newArticle = createNewArticle(article)
				articleService.deleteArticle(newArticle.id)
			}
		}

		context("게시글에 태그 추가") {
			val newArticle = createNewArticle(Mock.article())
			val newTag = createNewTag(Mock.tag())
			articleService.createArticleTag(newArticle.id, newTag.id)
			expect("태그 ID로 조회했을 때 해당 게시글이 조회된다.") {
				val relatedArticleList = articleService.getArticlesByTagId(newTag.id)
				relatedArticleList.size shouldBe 1
				relatedArticleList[0].id shouldBe newArticle.id
			}
			expect("게시글 조회할 때 관련된 태그들도 조회된다.") {
				val tagList = articleService.getTagsByArticleId(newArticle.id)
				tagList.size shouldBe 1
				tagList[0].name shouldBe newTag.name
			}
		}
	}

	fun createNewArticle(article: ArticleEntity) = articleService.createArticle(article)

	fun createNewTag(tag: TagEntity) = tagService.createTag(tag)

	fun validate(from: ArticleEntity, to: ArticleEntity) {
		from.title shouldBe to.title
		from.title shouldBe to.title
		from.summary shouldBe to.summary
		from.link shouldBe to.link
		from.hits shouldBe to.hits
		from.menuTag shouldBe to.menuTag
		from.hits shouldBe to.hits
		from.linkHits shouldBe to.linkHits
		from.isPublished shouldBe to.isPublished
		from.createdAt shouldNotBe null
		from.updatedAt shouldNotBe null
	}
}
