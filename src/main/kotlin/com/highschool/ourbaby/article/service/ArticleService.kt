package com.highschool.ourbaby.article.service

import com.highschool.ourbaby.article.dto.ArticleRequestDto
import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
	fun getAllArticles(): List<ArticleEntity> {
		return articleRepository.findAll()
	}

	fun getArticleById(id: Long): ArticleEntity {
		return articleRepository.findById(id).get()
	}

	fun createArticle(body: ArticleEntity): ArticleEntity {
		return articleRepository.save(body)
	}

	fun updateArticle(id: Long, body: ArticleEntity): ArticleEntity {
		val article = getArticleById(id)
		val updatedArticle = updateArticleProperties(article, body)
		return articleRepository.save(updatedArticle)
	}

	fun deleteArticle(id: Long): ArticleEntity {
		val article = getArticleById(id)
		articleRepository.deleteById(id)
		return article
	}

	private fun updateArticleProperties(origin: ArticleEntity, incoming: ArticleEntity): ArticleEntity {
		origin.title = incoming.title
		origin.summary = incoming.summary
		origin.link = incoming.link
		origin.menuTag = incoming.menuTag
		origin.hits = incoming.hits
		origin.linkHits = incoming.linkHits
		origin.isPublished = incoming.isPublished
		return origin
	}
}