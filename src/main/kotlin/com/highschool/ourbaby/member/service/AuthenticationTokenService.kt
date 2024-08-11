package com.highschool.ourbaby.member.service

import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationTokenService(
    val jwtService: JwtService,
) {
    fun refreshToken(refreshToken: String) =
        jwtService
            .extractClaims(refreshToken)
            .id
            .toLong()
            .let { jwtService.createAccessToken(now = Date(), userId = it) }
}
