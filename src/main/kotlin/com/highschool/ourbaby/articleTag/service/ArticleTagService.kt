package com.highschool.ourbaby.articleTag.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.articleTag.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.service.TagService
import org.springframework.stereotype.Service

@Service
class ArticleTagService(
	private val articleService: ArticleService,
	private val tagService: TagService,
	private val articleTagRepository: ArticleTagRepository,
) {
	fun getAllArticleTags(): List<ArticleTagEntity> = articleTagRepository.findAll()

	fun getArticlesByTagId(id: Long): List<ArticleEntity> {
		return articleTagRepository.findArticlesByTagId(id).map { it -> it.article }
	}

	fun getTagsByArticleId(id: Long): List<TagEntity> {
		return articleTagRepository.findTagsByArticleId(id).map { it -> it.tag }
	}

	fun createArticleTag(articleId: Long, tagId: Long): ArticleTagEntity {
		val article = articleService.getArticleById(articleId)
		val tag = tagService.getTagById(tagId)
		return articleTagRepository.save(ArticleTagEntity(article = article, tag = tag))
	}

	fun deleteArticleTag(articleId: Long, tagId: Long) =
		articleTagRepository.deleteByArticleIdAndTagId(articleId, tagId)

}
