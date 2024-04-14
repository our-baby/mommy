package com.highschool.ourbaby.tag.dto

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import java.time.LocalDateTime

data class TagRequestDto(
	val id: Long,
	val name: String,
) {
	fun toEntity() = TagEntity(id = this.id, name = this.name)
}
