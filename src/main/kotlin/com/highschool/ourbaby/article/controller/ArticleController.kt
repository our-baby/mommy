package com.highschool.ourbaby.article.controller

import com.highschool.ourbaby.article.dto.ArticleRequestDto
import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.article.dto.ArticleTagRequestDto
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.tag.dto.TagResponseDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(private val articleService: ArticleService) {

	@GetMapping
	fun getAllArticles() = articleService.getAllArticles().map { it -> ArticleResponseDto.fromEntity(it) }


	@GetMapping("/{id}")
	fun getArticleById(@PathVariable(value = "id", required = true) id: Long): ArticleResponseDto {
		val articleResponseDto = ArticleResponseDto.fromEntity(articleService.getArticleById(id))
		articleResponseDto.tagList =  articleService.getTagsByArticleId(id).map { it -> TagResponseDto.fromEntity(it) }
		return articleResponseDto
	}

	@GetMapping("/tag/{id}")
	fun getArticlesByTagId(@PathVariable id: Long): List<ArticleResponseDto> {
		return articleService.getArticlesByTagId(id).map { it -> ArticleResponseDto.fromEntity(it) }
	}

	@PostMapping
	fun createArticle(@RequestBody articleRequestDto: ArticleRequestDto) =
		ArticleResponseDto.fromEntity(articleService.createArticle(articleRequestDto.toEntity()))

	@PostMapping("/tags")
	fun createArticleTag(@RequestBody articleTagRequestDto: ArticleTagRequestDto) =
		articleService.createArticleTag(articleTagRequestDto.articleId, articleTagRequestDto.tagId)

	@PutMapping("/{id}")
	fun updateArticle(
		@PathVariable(value = "id", required = true) id: Long,
		@RequestBody articleRequestDto: ArticleRequestDto
	) = ArticleResponseDto.fromEntity(articleService.updateArticle(id, articleRequestDto.toEntity()))


	@DeleteMapping("/{id}")
	fun deleteArticle(@PathVariable(value = "id", required = true) id: Long) = articleService.deleteArticle(id)

	@DeleteMapping("/tags")
	fun deleteArticleTag(@RequestParam articleId: Long, @RequestParam tagId: Long) =
		articleService.deleteArticleTag(articleId, tagId)
}
