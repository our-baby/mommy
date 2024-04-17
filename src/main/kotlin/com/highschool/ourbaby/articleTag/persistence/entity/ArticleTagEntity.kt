package com.highschool.ourbaby.articleTag.persistence.entity

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.articleTag.dto.ArticleTagResponseDto
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "article_tag")
class ArticleTagEntity (
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long,

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "article_id")
	val article: ArticleEntity,

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "tag_id")
	val tag: TagEntity,
): BaseEntity() {
	fun toDto() = ArticleTagResponseDto(
		id = this.id,
		article = this.article.toDto(),
		tag = this.tag.toDto(),
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)
}
