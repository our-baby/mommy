package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.ErrorCode.REFRESH_TOKEN_INVALID

class RefreshTokenInvalidException(
    message: String,
) : RuntimeException(message) {
    val errorCode: ErrorCode = REFRESH_TOKEN_INVALID
}
