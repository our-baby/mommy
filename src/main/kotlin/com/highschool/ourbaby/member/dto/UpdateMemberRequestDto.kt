package com.highschool.ourbaby.member.dto

import com.highschool.ourbaby.member.domain.JoinType
import com.highschool.ourbaby.member.domain.UpdateMember

data class UpdateMemberRequestDto(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val joinType: JoinType,
) {
    fun toDomain() =
        UpdateMember(
            id = id,
            email = email,
            name = name,
            nickname = nickname,
            joinType = joinType,
        )
}
