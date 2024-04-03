package com.highschool.ourbaby.article.controller

import com.highschool.ourbaby.article.dto.ArticleRequestDto
import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.article.service.ArticleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(private val articleService: ArticleService) {

	@GetMapping("")
	fun getAllArticles(): List<ArticleResponseDto> {
		return articleService.getAllArticles().map { it -> it.toDto() }
	}

	@GetMapping("/{id}")
	fun getArticleById(@PathVariable(value = "id", required = true) id: Long): ArticleResponseDto {
		return articleService.getArticleById(id).toDto()
	}

	@PostMapping("")
	fun createArticle(@RequestBody body: ArticleRequestDto): ArticleResponseDto {
		return articleService.createArticle(body.toEntity()).toDto()
	}

	@PutMapping("/{id}")
	fun updateArticle(
		@PathVariable(value = "id", required = true) id: Long,
		@RequestBody body: ArticleRequestDto
	): ArticleResponseDto {
		return articleService.updateArticle(id, body.toEntity()).toDto()
	}

	@DeleteMapping("/{id}")
	fun deleteArticle(@PathVariable(value = "id", required = true) id: Long): ArticleResponseDto {
		return articleService.deleteArticle(id).toDto()
	}
}