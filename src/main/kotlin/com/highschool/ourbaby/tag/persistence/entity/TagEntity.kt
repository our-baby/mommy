package com.highschool.ourbaby.tag.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.tag.dto.TagResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@Entity
class TagEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long,
	@Column(nullable = false, length = 10)
	var name: String,
) : BaseEntity() {
	fun toDto() = TagResponseDto(
		id = this.id,
		name = this.name,
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)
}
