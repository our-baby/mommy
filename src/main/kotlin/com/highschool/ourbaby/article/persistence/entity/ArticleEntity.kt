package com.highschool.ourbaby.article.persistence.entity

import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.FetchType.LAZY

@Entity
@Table(name = "article")
class ArticleEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long = 0,
	@Column(nullable = false, length = 50)
	val title: String,
	@Column(columnDefinition = "TEXT")
	val summary: String,
	@Column(nullable = false, columnDefinition = "TEXT")
	val link: String,
	@Column(nullable = false)
	val hits: Int = 0,
	@Column(name = "link_hits", nullable = false)
	val linkHits: Int = 0,
	@Column(name = "is_published")
	val isPublished: Boolean = false,

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "category_id")
	val category: CategoryEntity,
) : BaseEntity()
