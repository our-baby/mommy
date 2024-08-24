package com.highschool.ourbaby.category.persistence.repository

import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long>
