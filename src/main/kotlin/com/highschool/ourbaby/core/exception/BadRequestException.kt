package com.highschool.ourbaby.core.exception

import com.highschool.ourbaby.core.exception.ErrorCode.BAD_REQUEST

class BadRequestException(
    message: String,
) : RuntimeException(message) {
    val errorCode: ErrorCode = BAD_REQUEST
}
