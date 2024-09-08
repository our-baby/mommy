package com.highschool.ourbaby.core.exception.dto

import com.highschool.ourbaby.core.exception.ErrorCode

class ExceptionResponseDto(
    _errorCode: ErrorCode,
    var message: String? = null,
) {
    init {
        if (message == null || message!!.isEmpty()) {
            message = _errorCode.message
        }
    }

    val errorCode = _errorCode.code
}
