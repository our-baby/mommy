package com.highschool.ourbaby.tag.dto

import com.highschool.ourbaby.tag.persistence.entity.TagEntity

data class TagResponseDto(
	val id: Long,
	val name: String,
) {
	constructor(tagEntity: TagEntity): this(
		tagEntity.id,
		tagEntity.name,
	)
}
