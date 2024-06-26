package com.highschool.ourbaby.tag.controller

import com.highschool.ourbaby.tag.dto.TagRequestDto
import com.highschool.ourbaby.tag.dto.TagResponseDto
import com.highschool.ourbaby.tag.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tags")
class TagController(
	private val tagService: TagService,
) {
	@GetMapping
	fun getAllTags() = tagService.getAllTags().map { TagResponseDto(it) }

	@GetMapping("/{id}")
	fun getTagById(@PathVariable(name = "id", required = true) id: Long) =
		TagResponseDto(tagService.getTagById(id))

	@PostMapping
	fun createTag(@RequestBody tagRequestDto: TagRequestDto) =
		TagResponseDto(tagService.createTag(tagRequestDto.toEntity()))

	@PutMapping("/{id}")
	fun updateTag(@PathVariable id: Long, @RequestBody tagRequestDto: TagRequestDto) =
		TagResponseDto(tagService.updateTag(id, tagRequestDto.toEntity()))

	@DeleteMapping("/{id}")
	fun deleteTag(@PathVariable id: Long) = tagService.deleteTag(id)
}
