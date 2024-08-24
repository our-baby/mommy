package com.highschool.ourbaby.searchHistory.service

import com.highschool.ourbaby.member.service.MemberService
import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import com.highschool.ourbaby.searchHistory.persistence.repository.SearchHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchHistoryService(
    private val memberService: MemberService,
    private val searchHistoryRepository: SearchHistoryRepository,
) {
    fun getSearchHistoriesByMemberId(id: Long): List<SearchHistoryEntity> = searchHistoryRepository.findByMemberId(id)

    fun createSearchHistory(
        keyword: String,
        memberId: Long,
    ): SearchHistoryEntity {
        val member = memberService.getMemberById(memberId)
        return searchHistoryRepository.save(SearchHistoryEntity(keyword = keyword, member = member))
    }

    @Transactional
    fun deleteByMemberId(id: Long) = searchHistoryRepository.deleteByMemberId(id)

    fun deleteById(id: Long) = searchHistoryRepository.deleteById(id)
}
