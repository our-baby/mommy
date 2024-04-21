package com.highschool.ourbaby.tag.service

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.persistence.repository.TagRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TagService(private val tagRepository: TagRepository) {

	fun getAllTags(): List<TagEntity> = tagRepository.findAll()

	fun getTagById(id: Long) =
		tagRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Tag with id $id")

	fun getArticlesByTag(id: Long) = getTagById(id).articleTags.map { it -> it.article }

	fun createTag(incomingTag: TagEntity) = tagRepository.save(incomingTag)

	fun updateTag(id: Long, incomingTag: TagEntity): TagEntity {
		val originalTag = getTagById(id)
		val updatedTag = updateTagProperties(originalTag, incomingTag)
		return tagRepository.save(updatedTag)
	}

	fun deleteTag(id: Long): TagEntity {
		val tag = getTagById(id)
		tagRepository.deleteById(id)
		return tag
	}

	private fun updateTagProperties(origin: TagEntity, incomingTag: TagEntity): TagEntity {
		origin.name = incomingTag.name
		return origin
	}
}
