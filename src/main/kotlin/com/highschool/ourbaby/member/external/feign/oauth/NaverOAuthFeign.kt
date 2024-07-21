package com.highschool.ourbaby.member.external.feign.oauth

import com.highschool.ourbaby.member.external.feign.oauth.data.NaverResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "NaverFeign", url = "\${feign.oauth.client.naver.user-info-url}")
interface NaverOAuthFeign {
    @GetMapping("v1/nid/me", consumes = ["application/xml"])
    fun getNaverUserInfo(
        @RequestHeader("Authorization") authCode: String,
    ): NaverResponse
}