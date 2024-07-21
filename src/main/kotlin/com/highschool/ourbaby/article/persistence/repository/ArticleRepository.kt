package com.highschool.ourbaby.article.persistence.repository

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ArticleRepository : JpaRepository<ArticleEntity, Long> {
	fun findByCategoryId(@Param("categoryId") categoryId: Long): List<ArticleEntity>
}
