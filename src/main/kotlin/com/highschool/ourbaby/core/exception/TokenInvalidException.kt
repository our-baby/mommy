package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.ErrorCode.TOKEN_INVALID

class TokenInvalidException(
    message: String,
) : RuntimeException(message) {
    val errorCode: ErrorCode = TOKEN_INVALID
}
