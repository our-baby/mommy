package com.highschool.ourbaby.article.persistence.repository

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<ArticleEntity, Long> {
}
