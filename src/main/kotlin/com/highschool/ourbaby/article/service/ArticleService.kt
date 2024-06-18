package com.highschool.ourbaby.article.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ArticleService(
	private val articleRepository: ArticleRepository,
) {
	fun getAllArticles(): List<ArticleEntity> = articleRepository.findAll()

	fun getArticleById(id: Long) =
		articleRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Article with id $id")

	fun createArticle(incomingArticle: ArticleEntity) = articleRepository.save(incomingArticle)

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

	fun deleteArticle(id: Long) = articleRepository.deleteById(id)

}
