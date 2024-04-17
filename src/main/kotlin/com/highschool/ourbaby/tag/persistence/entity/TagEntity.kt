package com.highschool.ourbaby.tag.persistence.entity

import com.highschool.ourbaby.articleTag.persistence.entity.ArticleTagEntity
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.tag.dto.TagResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tag")
class TagEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tag_id")
	val id: Long,
	@Column(nullable = false, length = 10)
	var name: String,
	@OneToMany(mappedBy = "tag")
	var articleTags: List<ArticleTagEntity> = ArrayList<ArticleTagEntity>(),
) : BaseEntity() {
	fun toDto() = TagResponseDto(
		id = this.id,
		name = this.name,
		articles = this.articleTags.map { it -> it.article.toDto() },
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)
}
