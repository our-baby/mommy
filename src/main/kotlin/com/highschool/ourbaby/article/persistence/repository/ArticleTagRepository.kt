package com.highschool.ourbaby.article.persistence.repository

import com.highschool.ourbaby.article.persistence.entity.ArticleTagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ArticleTagRepository : JpaRepository<ArticleTagEntity, Long> {
	@Query("SELECT at FROM ArticleTagEntity at JOIN FETCH at.tag t WHERE at.article.id = :articleId")
	fun findTagByArticleId(@Param("articleId") articleId: Long): List<ArticleTagEntity>
	fun findByArticleIdAndTagId(articleId: Long, tagId: Long): ArticleTagEntity?

	@Transactional
	fun deleteByArticleIdAndTagId(articleId: Long, tagId: Long)
}