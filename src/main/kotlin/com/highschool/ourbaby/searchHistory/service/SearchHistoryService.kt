package com.highschool.ourbaby.searchHistory.service

import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import com.highschool.ourbaby.searchHistory.persistence.repository.SearchHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchHistoryService(private val searchHistoryRepository: SearchHistoryRepository) {

	fun getSearchHistoriesByMemberId(id: Long): List<SearchHistoryEntity> = searchHistoryRepository.findByMember(id)

	@Transactional
	fun createSearchHistory(keyword: String, memberId: Long): SearchHistoryEntity {
		// TODO: get member entity from memberService
		return searchHistoryRepository.save(SearchHistoryEntity(keyword = keyword, member = memberId))
	}

	@Transactional
	fun deleteByMemberId(id: Long) = searchHistoryRepository.deleteByMember(id)

	@Transactional
	fun deleteById(id: Long) = searchHistoryRepository.deleteById(id)
}
