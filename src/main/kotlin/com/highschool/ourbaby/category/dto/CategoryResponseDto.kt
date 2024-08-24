package com.highschool.ourbaby.category.dto

import com.highschool.ourbaby.category.persistence.entity.CategoryEntity

data class CategoryResponseDto(
    val id: Long,
    val name: String,
) {
    constructor(categoryEntity: CategoryEntity) : this(
        categoryEntity.id,
        categoryEntity.name,
    )
}
