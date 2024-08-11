package com.highschool.ourbaby.member.service

import com.highschool.ourbaby.core.config.JwtConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    private val jwtConfig: JwtConfig,
) {
    fun createAccessToken(
        now: Date,
        userId: Long,
    ): String =
        Jwts
            .builder()
            .claims(
                Jwts
                    .claims()
                    .id(userId.toString())
                    .build(),
            ).issuer("mommy")
            .issuedAt(now)
            .expiration(Date(now.time + jwtConfig.accessTokenExpirationPeriod))
            .signWith(getSigningKey())
            .compact()

    private fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtConfig.secretKey)

        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun createRefreshToken(
        now: Date,
        userId: Long,
    ): String =
        Jwts
            .builder()
            .claims(
                Jwts
                    .claims()
                    .id(userId.toString())
                    .build(),
            ).issuer("mommy")
            .issuedAt(now)
            .expiration(Date(now.time + jwtConfig.refreshTokenExpirationPeriod))
            .signWith(getSigningKey())
            .compact()

    fun extractClaims(token: String): Claims {
        check(token.startsWith("Bearer "))

        return runCatching {
            Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token.replace("Bearer ", ""))
                .payload
        }.getOrElse { throw IllegalStateException("유효하지 않은 토큰입니다.") }
    }
}
