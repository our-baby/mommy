package com.highschool.ourbaby.core.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    BAD_REQUEST("400001", "잘못된 요청입니다."),
    NOT_FOUND("404001", "요청하는 리소스가 없습니다."),
    TOKEN_INVALID("400002", "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_INVALID("400003", "유효하지 않은 리프레쉬 토큰입니다."),
    UNKNOWN_ERROR("-999999", "알 수 없는 에러입니다."),
}
