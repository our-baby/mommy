package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "article")
class ArticleEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "article_id")
	val id: Long = 0,
	@Column(nullable = false, length = 50)
	var title: String,
	@Column(columnDefinition = "TEXT")
	var summary: String,
	@Column(nullable = false, columnDefinition = "TEXT")
	var link: String,
	@Column(name = "menu_tag", nullable = false, length = 10)
	var menuTag: String,
	@Column(nullable = false)
	var hits: Int = 0,
	@Column(name = "link_hits", nullable = false)
	var linkHits: Int = 0,
	@Column(name = "is_published")
	var isPublished: Boolean = false,
) : BaseEntity() {
	fun toDto() = ArticleResponseDto(
		title = this.title,
		summary = this.summary,
		link = this.link,
		menuTag = this.menuTag,
		hits = this.hits,
		linkHits = this.linkHits,
		isPublished = this.isPublished,
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)

	fun update(incoming: ArticleEntity) {
		this.title = incoming.title
		this.summary = incoming.summary
		this.link = incoming.link
		this.menuTag = incoming.menuTag
		this.hits = incoming.hits
		this.linkHits = incoming.linkHits
		this.isPublished = incoming.isPublished
	}
}
