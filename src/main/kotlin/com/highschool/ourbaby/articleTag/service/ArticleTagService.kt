package com.highschool.ourbaby.articleTag.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.articleTag.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.category.service.CategoryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleTagService(
	private val articleService: ArticleService,
	private val categoryService: CategoryService,
	private val articleTagRepository: ArticleTagRepository,
) {
	fun getAllArticleTags(): List<ArticleTagEntity> = articleTagRepository.findAll()

	fun getArticlesByTagId(id: Long): List<ArticleEntity> {
		return articleTagRepository.findArticlesByTagId(id).map { it -> it.article }
	}

	fun getTagsByArticleId(id: Long): List<CategoryEntity> {
		return articleTagRepository.findTagsByArticleId(id).map { it -> it.tag }
	}

	fun createArticleTag(articleId: Long, tagId: Long): ArticleTagEntity {
		val article = articleService.getArticleById(articleId)
		val tag = categoryService.getCategoryById(tagId)
		return articleTagRepository.save(ArticleTagEntity(article = article, tag = tag))
	}

	@Transactional
	fun deleteArticleTag(articleId: Long, tagId: Long) =
		articleTagRepository.deleteByArticleIdAndTagId(articleId, tagId)

}
