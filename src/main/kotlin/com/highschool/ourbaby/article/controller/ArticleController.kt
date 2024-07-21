package com.highschool.ourbaby.article.controller

import com.highschool.ourbaby.article.dto.ArticleRequestDto
import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.article.service.ArticleService
import com.highschool.ourbaby.category.service.CategoryService
import com.highschool.ourbaby.core.exception.BadRequestException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException.BadRequest

@RestController
@RequestMapping("/api/articles")
class ArticleController(
	private val categoryService: CategoryService,
	private val articleService: ArticleService,
) {

	@GetMapping
	fun getAllArticles() = articleService.getAllArticles().map { ArticleResponseDto(it) }


	@GetMapping("/{id}")
	fun getArticleById(@PathVariable(value = "id", required = true) id: Long) =
		ArticleResponseDto(articleService.getArticleById(id))

	@GetMapping("/categories/{id}")
	fun getArticlesByCategoryId(@PathVariable(value = "id", required = true) id: Long) =
		articleService.getArticlesByCategoryId(id).map { ArticleResponseDto(it) }


	@PostMapping
	fun createArticle(@RequestBody articleRequestDto: ArticleRequestDto): ArticleResponseDto {
		articleRequestDto.categoryId ?: throw BadRequestException("No categoryId")
		val cateogry = categoryService.getCategoryById(articleRequestDto.categoryId)
		return ArticleResponseDto(
			articleService.createArticle(
				articleRequestDto.title,
				articleRequestDto.summary,
				articleRequestDto.link,
				articleRequestDto.hits,
				articleRequestDto.linkHits,
				articleRequestDto.isPublished,
				cateogry,
			)
		)
	}

	@PutMapping("/{id}")
	fun updateArticle(
		@PathVariable(value = "id", required = true) id: Long,
		@RequestBody articleRequestDto: ArticleRequestDto
	): ArticleResponseDto {
		val category = articleRequestDto.categoryId?.let {
			categoryService.getCategoryById(articleRequestDto.categoryId)
		}
		return ArticleResponseDto(
			articleService.updateArticle(
				id,
				articleRequestDto.title,
				articleRequestDto.summary,
				articleRequestDto.link,
				articleRequestDto.hits,
				articleRequestDto.linkHits,
				articleRequestDto.isPublished,
				category
			)
		)
	}


	@DeleteMapping("/{id}")
	fun deleteArticle(@PathVariable(value = "id", required = true) id: Long) = articleService.deleteArticle(id)
}
