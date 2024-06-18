package com.highschool.ourbaby.searchHistory

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import com.highschool.ourbaby.searchHistory.persistence.repository.SearchHistoryRepository
import com.highschool.ourbaby.searchHistory.service.SearchHistoryService
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.ContextConfiguration

@EnableJpaAuditing
@DataJpaTest
@ContextConfiguration(classes = [SpringDataConfig::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SearchHistoryServiceSpec(
	private val searchHistoryRepository: SearchHistoryRepository,
) : ExpectSpec() {
	private val searchHistoryService = SearchHistoryService(searchHistoryRepository)

	init {
		context("검색기록 추가") {
			val memberId = 10L
			val searchHistoryList = createSearchHistory(memberId)
			expect("사용자 아이디로 조회하면 추가한 검색기록들이 조회된다.") {
				val searchHistoryListByMember = searchHistoryService.getSearchHistoriesByMemberId(memberId)
				val searchHistoryKeywordList = searchHistoryList.map { it -> it.id }
				val searchHistoryKeywordListByMember = searchHistoryListByMember.map { it -> it.id }
				searchHistoryList.size shouldBe searchHistoryListByMember.size
				(searchHistoryKeywordList equalsIgnoreOrder searchHistoryKeywordListByMember) shouldBe true
			}
			expect("사용자 아이디로 전부 삭제") {
				searchHistoryService.deleteByMemberId(memberId)
			}
		}
	}

	fun createSearchHistory(memberId: Long): ArrayList<SearchHistoryEntity> {
		val searchHistoryList =
			arrayListOf<SearchHistoryEntity>(Mock.searchHistory(memberId), Mock.searchHistory(memberId))
		val entityList: ArrayList<SearchHistoryEntity> = arrayListOf<SearchHistoryEntity>()
		for (searchHistory in searchHistoryList) {
			entityList.addLast(searchHistoryRepository.save(searchHistory))
		}
		return entityList
	}

	infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) = this.size == other.size && this.toSet() == other.toSet()
}
