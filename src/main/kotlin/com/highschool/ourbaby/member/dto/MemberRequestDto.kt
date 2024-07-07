package com.highschool.ourbaby.member.dto

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.member.domain.JoinType
import com.highschool.ourbaby.member.persistence.entity.MemberEntity

data class MemberRequestDto(
	val email: String,
	val name: String,
	val nickname: String,
	val joinType: JoinType,
) {
	fun toEntity() = MemberEntity(
		email = this.email,
		name = this.name,
		nickname = this.nickname,
		joinType = this.joinType,
	)
}
