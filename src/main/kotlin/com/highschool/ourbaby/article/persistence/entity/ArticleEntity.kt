package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ArticleEntity(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,
	@Column(nullable = false, length = 50)
	var title: String,
	@Column(columnDefinition = "TEXT")
	var summary: String,
	@Column(nullable = false, columnDefinition = "TEXT")
	var link: String,
	@Column(name="menu_tag", nullable = false, length = 10)
	var menuTag: String,
	@Column(nullable = false)
	var hits: Int = 0,
	@Column(name = "link_hits", nullable = false)
	var linkHits: Int = 0,
	@Column(name = "is_published")
	var isPublished: Boolean = false,
) {
	fun toDto(): ArticleResponseDto {
		return ArticleResponseDto(
			id = this.id,
			title = this.title,
			summary = this.summary,
			link = this.link,
			menuTag = this.menuTag,
			hits = this.hits,
			linkHits = this.linkHits,
			isPublished = this.isPublished,
		)
	}
}