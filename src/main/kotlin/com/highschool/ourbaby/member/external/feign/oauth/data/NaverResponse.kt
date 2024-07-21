package com.highschool.ourbaby.member.external.feign.oauth.data

class NaverResponse(
    val resultCode: String,
    val message: String,
    val result: NaverUserInfoResponse,
) {
    fun toDomain() = result.toDomain()
}
