package com.highschool.ourbaby.searchHistory.dto

import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity

data class SearchHistoryResponseDto(
	val id: Long,
	val keyword: String,
	val member: Long,
) {
	constructor(searchHistoryEntity: SearchHistoryEntity): this(
		searchHistoryEntity.id,
		searchHistoryEntity.keyword,
		searchHistoryEntity.member,
	)
}
