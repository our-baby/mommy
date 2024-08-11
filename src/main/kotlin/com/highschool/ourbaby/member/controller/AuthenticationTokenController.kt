package com.highschool.ourbaby.member.controller

import com.highschool.ourbaby.member.dto.RefreshTokenResponseDto
import com.highschool.ourbaby.member.service.AuthenticationTokenService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members/tokens")
class AuthenticationTokenController(
    private val authenticationTokenService: AuthenticationTokenService,
) {
    @PostMapping("refresh")
    fun refreshToken(
        @RequestHeader("Authorization-Refresh") refreshToken: String,
    ) = RefreshTokenResponseDto(
        accessToken = authenticationTokenService.refreshToken("Bearer $refreshToken"),
    )
}
