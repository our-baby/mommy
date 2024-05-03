package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.article.dto.ArticleTagResponseDto
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "article_tag")
class ArticleTagEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long = 0,

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "article_id")
	var article: ArticleEntity,

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "tag_id")
	var tag: TagEntity,
) : BaseEntity() {
	fun toDto() = ArticleTagResponseDto(
		id = this.id,
		article = this.article.toDto(),
		tag = this.tag.toDto(),
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)

	fun update(article: ArticleEntity, tag: TagEntity) {
		this.article = article
		this.tag = tag
	}
}
