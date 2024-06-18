package com.highschool.ourbaby.article.persistence.repository

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, Long> {
}
