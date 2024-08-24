package com.highschool.ourbaby.core.response

class ApiResponse<T>(
    val data: T,
) {
    val success: Boolean = true
}
