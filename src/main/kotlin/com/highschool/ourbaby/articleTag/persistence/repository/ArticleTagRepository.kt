package com.highschool.ourbaby.articleTag.persistence.repository

import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface ArticleTagRepository: JpaRepository<ArticleTagEntity, Long> {
	fun findByArticle_IdAndTag_Id(articleId: Long, tagId: Long): ArticleTagEntity
	@Transactional
	fun deleteByArticle_IdAndTag_Id(articleId: Long, tagId: Long)
}