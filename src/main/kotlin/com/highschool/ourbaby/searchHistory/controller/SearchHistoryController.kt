package com.highschool.ourbaby.searchHistory.controller

import com.highschool.ourbaby.searchHistory.dto.SearchHistoryRequestDto
import com.highschool.ourbaby.searchHistory.dto.SearchHistoryResponseDto
import com.highschool.ourbaby.searchHistory.service.SearchHistoryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search-histories")
class SearchHistoryController(
	private val searchHistoryService: SearchHistoryService,
) {
	@GetMapping("/{id}")
	fun getSearchHistoriesByMemberId(@PathVariable id: Long) =
		searchHistoryService.getSearchHistoriesByMemberId(id).map { SearchHistoryResponseDto(it) }

	@PostMapping
	fun createSearchHistory(@RequestBody searchHistoryRequestDto: SearchHistoryRequestDto) =
		SearchHistoryResponseDto(
			searchHistoryService.createSearchHistory(
				searchHistoryRequestDto.keyword,
				searchHistoryRequestDto.memberId
			)
		)

	@DeleteMapping("/{id}")
	fun deleteById(@PathVariable id: Long) = searchHistoryService.deleteById(id)

	@DeleteMapping("/members/{id}")
	fun deleteByMemberId(@PathVariable id: Long) = searchHistoryService.deleteByMemberId(id)
}
