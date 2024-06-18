package com.highschool.ourbaby.tag.service

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.persistence.repository.TagRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TagService(
	private val tagRepository: TagRepository,
) {

	fun getAllTags(): List<TagEntity> = tagRepository.findAll()

	fun getTagById(id: Long) =
		tagRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Tag with id $id")

	fun createTag(incomingTag: TagEntity) = tagRepository.save(incomingTag)

	fun updateTag(id: Long, incoming: TagEntity): TagEntity {
		val origin = getTagById(id)
		val update = TagEntity(
			id = origin.id,
			name = incoming.name,
		)
		update.createdAt = origin.createdAt
		return tagRepository.save(update)
	}

	fun deleteTag(id: Long) = tagRepository.deleteById(id)

}
