package com.highschool.ourbaby.articleTag.dto

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import java.time.LocalDateTime

data class ArticleTagRequestDto (
	val id: Long,
	val articleId: Long,
	val tagId: Long,
)
