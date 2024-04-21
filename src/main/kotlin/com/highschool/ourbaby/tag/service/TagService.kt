package com.highschool.ourbaby.tag.service

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.persistence.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class TagService(private val tagRepository: TagRepository) {

	fun getAllTags(): List<TagEntity> = tagRepository.findAll()

	fun getTagById(id: Long) =
		tagRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Tag with id $id")

	@Transactional
	fun createTag(incomingTag: TagEntity) = tagRepository.save(incomingTag)

	@Transactional
	fun updateTag(id: Long, incoming: TagEntity): TagEntity {
		val origin = getTagById(id)
		return tagRepository.save(TagEntity(
			id = origin.id,
			name = origin.name,
		))
	}

	@Transactional
	fun deleteTag(id: Long): TagEntity {
		val tag = getTagById(id)
		tagRepository.deleteById(id)
		return tag
	}
}
