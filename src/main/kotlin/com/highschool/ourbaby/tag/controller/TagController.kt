package com.highschool.ourbaby.tag.controller

import com.highschool.ourbaby.tag.dto.TagRequestDto
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
class TagController(private val tagService: TagService) {
	@GetMapping
	fun getAllTags() = tagService.getAllTags().map { it -> it.toDto() }

	@GetMapping("/{id}")
	fun getTagById(@PathVariable(name = "id", required = true) id: Long) = tagService.getTagById(id).toDto()

	@PostMapping
	fun createTag(@RequestBody tagRequestDto: TagRequestDto) = tagService.createTag(tagRequestDto.toEntity()).toDto()

	@PutMapping("/{id}")
	fun updateTag(@PathVariable(name = "id", required = true) id: Long, @RequestBody tagRequestDto: TagRequestDto) =
		tagService.updateTag(id, tagRequestDto.toEntity()).toDto()

	@DeleteMapping("/{id}")
	fun deleteTag(@PathVariable(name = "id", required = true) id: Long) = tagService.deleteTag(id).toDto()
}
