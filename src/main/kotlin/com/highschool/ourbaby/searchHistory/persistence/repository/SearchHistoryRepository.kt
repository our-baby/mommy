package com.highschool.ourbaby.searchHistory.persistence.repository

import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface SearchHistoryRepository : JpaRepository<SearchHistoryEntity, Long> {
	fun findByMember(@Param("memberId") memberId: Long): List<SearchHistoryEntity>

	fun deleteByMember(@Param("memberId") memberId: Long)
}
