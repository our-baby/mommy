package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "article")
class ArticleEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "article_id")
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
	@OneToMany(mappedBy = "article")
	var articleTags: List<ArticleTagEntity> = ArrayList<ArticleTagEntity>(),
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
		tags = articleTags.map { it -> it.tag.name },
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)
}
