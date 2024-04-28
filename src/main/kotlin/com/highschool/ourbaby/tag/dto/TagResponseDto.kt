package com.highschool.ourbaby.tag.dto

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import java.time.LocalDateTime

data class TagResponseDto(
	val id: Long,
	val name: String,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	companion object Factory {
		fun fromEntity(tagEntity: TagEntity) = TagResponseDto(
			id = tagEntity.id,
			name = tagEntity.name,
			createdAt = tagEntity.createdAt,
			updatedAt = tagEntity.updatedAt,
		)
	}
}
