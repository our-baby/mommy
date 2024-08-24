package com.highschool.ourbaby.searchHistory.dto

import com.highschool.ourbaby.member.dto.MemberResponseDto
import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity

data class SearchHistoryResponseDto(
    val id: Long,
    val keyword: String,
    val member: MemberResponseDto,
) {
    constructor(searchHistoryEntity: SearchHistoryEntity) : this(
        searchHistoryEntity.id,
        searchHistoryEntity.keyword,
        MemberResponseDto(searchHistoryEntity.member),
    )
}
