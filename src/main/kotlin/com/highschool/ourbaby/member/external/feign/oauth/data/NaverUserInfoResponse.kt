package com.highschool.ourbaby.member.external.feign.oauth.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.highschool.ourbaby.member.domain.oauth.NaverUser

class NaverUserInfoResponse(
    val email: String,
    val name: String,
    @JsonProperty("profile_image")
    val profileImage: String,
) {
    fun toDomain() =
        NaverUser(
            email = email,
            name = name,
            profileImage = profileImage,
        )
}
