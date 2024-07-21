package com.highschool.ourbaby.member.external.feign.oauth.data

import com.highschool.ourbaby.member.domain.oauth.NaverUser

class NaverUserInfoResponse(
    val email: String,
    val name: String,
) {
    fun toDomain() = NaverUser(
        email = email,
        name = name,
    )
}
