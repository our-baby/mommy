package com.highschool.ourbaby.member.domain

class Member(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val joinType: JoinType,
)
