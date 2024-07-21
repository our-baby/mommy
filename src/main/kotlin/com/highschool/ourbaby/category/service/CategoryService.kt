package com.highschool.ourbaby.category.service

import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.category.persistence.repository.CategoryRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryService(
	private val categoryRepository: CategoryRepository,
) {

	fun getAllCategories(): List<CategoryEntity> = categoryRepository.findAll()

	fun getCategoryById(id: Long) =
		categoryRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Category with id $id")

	fun createCategory(incomingTag: CategoryEntity) = categoryRepository.save(incomingTag)

	fun updateCategory(id: Long, incoming: CategoryEntity): CategoryEntity {
		val origin = getCategoryById(id)
		val update = CategoryEntity(
			id = origin.id,
			name = incoming.name,
		)
		update.createdAt = origin.createdAt
		return categoryRepository.save(update)
	}

	fun deleteCategory(id: Long) = categoryRepository.deleteById(id)

}
