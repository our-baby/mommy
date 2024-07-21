package com.highschool.ourbaby.core.util

fun makeRandomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"

    return (1..length)
        .map { charset.random() }
        .joinToString("")
}
