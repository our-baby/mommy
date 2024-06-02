package com.highschool.ourbaby.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig(
    @Value("\${jwt.secretKey}")
    val secretKey: String,

    @Value("\${jwt.access.expiration}")
    val accessTokenExpirationPeriod: Long,

    @Value("\${jwt.refresh.expiration}")
    val refreshTokenExpirationPeriod: Long,

    @Value("\${jwt.access.header}")
    val accessHeader: String,

    @Value("\${jwt.refresh.header}")
    val refreshHeader: String,
)
