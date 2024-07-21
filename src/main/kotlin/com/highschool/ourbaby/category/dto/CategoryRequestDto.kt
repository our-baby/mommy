package com.highschool.ourbaby.category.dto

import com.highschool.ourbaby.category.persistence.entity.CategoryEntity

data class CategoryRequestDto(
	val name: String,
) {
	fun toEntity() = CategoryEntity(name = this.name)
}
