package com.highschool.ourbaby.article.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
	fun getAllArticles(): List<ArticleEntity> = articleRepository.findAll()

	fun getArticleById(id: Long) =
		articleRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Article with id $id")

	fun getTagsByArticle(id: Long) = getArticleById(id).articleTags.map { it -> it.tag }

	fun createArticle(incomingArticle: ArticleEntity) = articleRepository.save(incomingArticle)

	fun updateArticle(id: Long, incomingArticle: ArticleEntity): ArticleEntity {
		val article = getArticleById(id)
		val updatedArticle = updateArticleProperties(article, incomingArticle)
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
