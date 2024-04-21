package com.highschool.ourbaby.articleTag.controller

import com.highschool.ourbaby.articleTag.dto.ArticleTagRequestDto
import com.highschool.ourbaby.articleTag.dto.ArticleTagResponseDto
import com.highschool.ourbaby.articleTag.service.ArticleTagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/articleTags")
class ArticleTagController(private val articleTagService: ArticleTagService) {

	@GetMapping
	fun getAllArticleTags(): List<ArticleTagResponseDto> =
		articleTagService.getAllArticleTags().map { it -> it.toDto() }

	@PostMapping
	fun createArticleTag(@RequestBody articleTagRequestDto: ArticleTagRequestDto): ArticleTagResponseDto =
		articleTagService.createArticleTag(
			articleTagRequestDto.id,
			articleTagRequestDto.articleId,
			articleTagRequestDto.tagId
		).toDto()

	@DeleteMapping
	fun deleteArticleTag(@RequestParam articleId: Long, @RequestParam tagId: Long): ArticleTagResponseDto {
		return articleTagService.deleteArticleTag(articleId, tagId).toDto()
	}
}