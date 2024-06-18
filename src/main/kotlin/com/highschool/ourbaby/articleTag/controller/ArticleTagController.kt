package com.highschool.ourbaby.articleTag.controller

import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.articleTag.dto.ArticleTagRequestDto
import com.highschool.ourbaby.articleTag.dto.ArticleTagResponseDto
import com.highschool.ourbaby.articleTag.service.ArticleTagService
import com.highschool.ourbaby.tag.dto.TagResponseDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articleTags")
class ArticleTagController(
	private val articleTagService: ArticleTagService,
) {

	@GetMapping
	fun getAllArticleTags() =
		articleTagService.getAllArticleTags().map { ArticleTagResponseDto(it) }

	@GetMapping("/tags/{id}")
	fun getTagsByArticleId(@PathVariable id: Long) =
		articleTagService.getTagsByArticleId(id).map { TagResponseDto(it) }

	@GetMapping("/articles/{id}")
	fun getArticlesByTagId(@PathVariable id: Long) =
		articleTagService.getArticlesByTagId(id).map { ArticleResponseDto(it) }

	@PostMapping
	fun createArticleTag(@RequestBody articleTagRequestDto: ArticleTagRequestDto) =
		articleTagService.createArticleTag(articleTagRequestDto.articleId, articleTagRequestDto.tagId)

	@DeleteMapping
	fun deleteArticleTag(@RequestParam articleId: Long, @RequestParam tagId: Long) =
		articleTagService.deleteArticleTag(articleId, tagId)
}
