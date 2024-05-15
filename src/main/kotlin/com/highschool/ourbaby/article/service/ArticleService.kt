package com.highschool.ourbaby.article.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.article.persistence.repository.ArticleTagRepository
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.service.TagService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class ArticleService(
	private val articleRepository: ArticleRepository,
	private val tagService: TagService,
	private val articleTagRepository: ArticleTagRepository
) {
	fun getAllArticles(): List<ArticleEntity> = articleRepository.findAll()

	fun getTagListByArticleId(id: Long): List<TagEntity> =
		articleTagRepository.findTagByArticleId(id).map { it -> it.tag }

	fun getArticleById(id: Long) =
		articleRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Article with id $id")

	fun getArticlesByTagId(id: Long): List<ArticleEntity> {
		return articleTagRepository.findArticlesByTagId(id).map { it -> it.article }
	}

	fun getTagsByArticleId(id: Long): List<TagEntity> {
		return articleTagRepository.findTagsByArticleId(id).map { it -> it.tag }
	}

	@Transactional
	fun createArticle(incomingArticle: ArticleEntity) = articleRepository.save(incomingArticle)

	@Transactional
	fun createArticleTag(articleId: Long, tagId: Long): ArticleTagEntity {
		val article = getArticleById(articleId)
		val tag = tagService.getTagById(tagId)
		return articleTagRepository.save(ArticleTagEntity(article = article, tag = tag))
	}

	fun updateArticle(id: Long, incoming: ArticleEntity): ArticleEntity {
		val origin = getArticleById(id)
		val update = ArticleEntity(
			id = origin.id,
			title = incoming.title,
			summary = incoming.summary,
			link = incoming.link,
			menuTag = incoming.menuTag,
			hits = incoming.hits,
			linkHits = incoming.linkHits,
			isPublished = incoming.isPublished,
		)
		update.createdAt = origin.createdAt
		return articleRepository.save(update)
	}

	@Transactional
	fun deleteArticle(id: Long) {
		val article = getArticleById(id)
		articleRepository.deleteById(id)
	}

	@Transactional
	fun deleteArticleTag(articleId: Long, tagId: Long) {
		articleTagRepository.deleteByArticleIdAndTagId(articleId, tagId)
	}
}
