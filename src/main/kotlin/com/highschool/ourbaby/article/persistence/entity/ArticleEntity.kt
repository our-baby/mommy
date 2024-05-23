package com.highschool.ourbaby.article.persistence.entity

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
	val title: String,
	@Column(columnDefinition = "TEXT")
	val summary: String,
	@Column(nullable = false, columnDefinition = "TEXT")
	val link: String,
	@Column(name = "menu_tag", nullable = false, length = 10)
	val menuTag: String,
	@Column(nullable = false)
	val hits: Int = 0,
	@Column(name = "link_hits", nullable = false)
	val linkHits: Int = 0,
	@Column(name = "is_published")
	val isPublished: Boolean = false,
) : BaseEntity()
