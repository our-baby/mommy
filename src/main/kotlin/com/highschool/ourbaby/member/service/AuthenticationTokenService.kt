package com.highschool.ourbaby.member.service

import com.highschool.ourbaby.core.exception.RefreshTokenInvalidException
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationTokenService(
    val jwtService: JwtService,
) {
    fun refreshToken(refreshToken: String): String =
        runCatching {
            jwtService
                .extractClaims(refreshToken)
                .id
                .toLong()
                .let { jwtService.createAccessToken(now = Date(), userId = it) }
        }.onFailure { throw RefreshTokenInvalidException("리프레쉬 토큰이 유효하지 않습니다.") }
            .getOrThrow()
}
