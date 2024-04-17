package com.highschool.ourbaby.articleTag.persistence.repository

import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleTagRepository: JpaRepository<ArticleTagEntity, Long> {
}