package com.highschool.ourbaby.article.controller

import com.highschool.ourbaby.article.dto.ArticleRequestDto
import com.highschool.ourbaby.article.dto.ArticleResponseDto
import com.highschool.ourbaby.article.dto.ArticleTagRequestDto
import com.highschool.ourbaby.article.service.ArticleService
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
	fun getAllArticles() = articleService.getAllArticles().map { it -> it.toDto() }


	@GetMapping("/{id}")
	fun getArticleById(@PathVariable(value = "id", required = true) id: Long): ArticleResponseDto {
		val article = articleService.getArticleById(id).toDto()
		val tagList = articleService.getTagListByArticleId(id)
		return ArticleResponseDto(
			id = article.id,
			title = article.title,
			summary = article.summary,
			link = article.link,
			menuTag = article.menuTag,
			hits = article.hits,
			linkHits = article.linkHits,
			tagList = tagList.map { it -> it.toDto() },
			isPublished = article.isPublished,
			createdAt = article.createdAt,
			updatedAt = article.updatedAt,
		)
	}

	@GetMapping("/tags/{id}")
	fun getArticlesByTagId(@PathVariable id: Long): List<ArticleResponseDto> {
		return articleService.getArticlesByTagId(id).map { it -> it.toDto() }
	}

	@PostMapping
	fun createArticle(@RequestBody articleRequestDto: ArticleRequestDto) =
		articleService.createArticle(articleRequestDto.toEntity()).toDto()

	@PostMapping("/tags")
	fun createArticleTag(@RequestBody articleTagRequestDto: ArticleTagRequestDto) =
		articleService.createArticleTag(articleTagRequestDto.articleId, articleTagRequestDto.tagId)

	@PutMapping("/{id}")
	fun updateArticle(
		@PathVariable(value = "id", required = true) id: Long,
		@RequestBody articleRequestDto: ArticleRequestDto
	) = articleService.updateArticle(id, articleRequestDto.toEntity()).toDto()


	@DeleteMapping("/{id}")
	fun deleteArticle(@PathVariable(value = "id", required = true) id: Long) = articleService.deleteArticle(id)

	@DeleteMapping("/tags")
	fun deleteArticleTag(@RequestParam articleId: Long, @RequestParam tagId: Long) =
		articleService.deleteArticleTag(articleId, tagId)

}
