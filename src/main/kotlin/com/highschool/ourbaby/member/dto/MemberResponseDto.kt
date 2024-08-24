package com.highschool.ourbaby.member.dto

import com.highschool.ourbaby.member.domain.JoinType
import com.highschool.ourbaby.member.persistence.entity.MemberEntity

data class MemberResponseDto(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val joinType: JoinType,
    val profileImage: String,
) {
    constructor(memberEntity: MemberEntity) : this(
        memberEntity.id,
        memberEntity.email,
        memberEntity.name,
        memberEntity.nickname,
        memberEntity.joinType,
        memberEntity.profileImage,
    )
}
