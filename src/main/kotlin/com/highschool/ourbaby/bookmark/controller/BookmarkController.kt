package com.highschool.ourbaby.bookmark.controller

import com.highschool.ourbaby.bookmark.dto.BookmarkRequestDto
import com.highschool.ourbaby.bookmark.dto.BookmarkResponseDto
import com.highschool.ourbaby.bookmark.service.BookmarkService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController(
	val bookmarkService: BookmarkService,
) {
	@GetMapping
	fun getAllBookmarks() = bookmarkService.getAllBookmark().map { BookmarkResponseDto(it) }


	@GetMapping("/members/{id}")
	fun getArticlesByMemberId(@PathVariable id: Long) = bookmarkService.getArticlesByMemberId(id)

	@PostMapping
	fun createBookmark(@RequestBody bookmarkRequestDto: BookmarkRequestDto): BookmarkResponseDto {
		val bookmark = bookmarkService.createBookmark(
			bookmarkRequestDto.articleId,
			bookmarkRequestDto.memberId
		)
		return BookmarkResponseDto(bookmark)
	}

	@DeleteMapping
	fun deleteBookmark(@RequestParam articleId: Long, @RequestParam memberId: Long) {
		bookmarkService.deleteBookmark(articleId, memberId)
	}
}
