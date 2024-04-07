package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@Entity
class ArticleEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long,
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
		id = this.id,
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
}
