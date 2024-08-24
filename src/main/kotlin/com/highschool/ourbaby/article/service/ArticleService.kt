package com.highschool.ourbaby.article.service

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
) {
    fun getAllArticles(pageable: Pageable): Page<ArticleEntity> = articleRepository.findAll(pageable)

    fun getArticleById(id: Long) =
        articleRepository.findById(id).getOrNull()
            ?: throw NoSuchElementException("No Article with id $id")

    fun getArticlesByCategoryId(
        id: Long,
        pageable: Pageable,
    ): Page<ArticleEntity> = articleRepository.findByCategoryId(id, pageable)

    fun createArticle(
        title: String,
        summary: String,
        link: String,
        hits: Int,
        linkHits: Int,
        isPublished: Boolean,
        categoryEntity: CategoryEntity,
    ): ArticleEntity {
        val article =
            ArticleEntity(
                title = title,
                summary = summary,
                link = link,
                hits = hits,
                linkHits = linkHits,
                isPublished = isPublished,
                category = categoryEntity,
            )
        return articleRepository.save(article)
    }

    fun updateArticle(
        id: Long,
        title: String,
        summary: String,
        link: String,
        hits: Int,
        linkHits: Int,
        isPublished: Boolean,
        categoryEntity: CategoryEntity?,
    ): ArticleEntity {
        val origin = getArticleById(id)
        val update =
            ArticleEntity(
                id = origin.id,
                title = title,
                summary = summary,
                link = link,
                hits = hits,
                linkHits = linkHits,
                isPublished = isPublished,
                category = categoryEntity ?: origin.category,
            )
        update.createdAt = origin.createdAt
        return articleRepository.save(update)
    }

    fun deleteArticle(id: Long) = articleRepository.deleteById(id)
}
