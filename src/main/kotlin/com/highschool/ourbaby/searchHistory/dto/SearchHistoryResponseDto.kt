package com.highschool.ourbaby.searchHistory.dto

import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import java.time.LocalDateTime

data class SearchHistoryResponseDto(
	val id: Long,
	val keyword: String,
	val member: Long,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	companion object Factory {
		fun fromEntity(searchHistoryEntity: SearchHistoryEntity) = SearchHistoryResponseDto(
			id = searchHistoryEntity.id,
			keyword = searchHistoryEntity.keyword,
			member = searchHistoryEntity.member,
			createdAt = searchHistoryEntity.createdAt,
			updatedAt = searchHistoryEntity.updatedAt,
		)
	}
}
