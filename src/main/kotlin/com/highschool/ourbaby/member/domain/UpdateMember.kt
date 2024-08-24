package com.highschool.ourbaby.member.domain

class UpdateMember(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val joinType: JoinType,
)
