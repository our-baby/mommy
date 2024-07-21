package com.highschool.ourbaby.member.external.feign.oauth.data

import com.fasterxml.jackson.annotation.JsonProperty

class NaverResponse(
    @JsonProperty("resultcode")
    val resultCode: String,
    val message: String,
    val response: NaverUserInfoResponse,
) {
    fun toDomain() = response.toDomain()
}
