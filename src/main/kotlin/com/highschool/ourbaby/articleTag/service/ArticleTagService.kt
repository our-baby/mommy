package com.highschool.ourbaby.articleTag.service

import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.articleTag.dto.ArticleTagRequestDto
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.articleTag.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.tag.service.TagService
import org.springframework.stereotype.Service

@Service
class ArticleTagService(
	private val articleTagRepository: ArticleTagRepository,
	private val articleService: ArticleService,
	private val tagService: TagService,
) {
	fun getAllArticleTags(): List<ArticleTagEntity> = articleTagRepository.findAll()

	fun createArticleTag(id: Long, articleId: Long, tagId: Long): ArticleTagEntity {
		val article = articleService.getArticleById(articleId)
		val tag = tagService.getTagById(tagId)
		return articleTagRepository.save(ArticleTagEntity(id = id, article = article, tag = tag))
	}

	fun deleteArticleTag(articleId: Long, tagId: Long): ArticleTagEntity {
		val articleTag = articleTagRepository.findByArticle_IdAndTag_Id(articleId, tagId)
		articleTagRepository.deleteByArticle_IdAndTag_Id(articleId, tagId)
		return articleTag
	}
}