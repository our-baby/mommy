package com.highschool.ourbaby.core.exception

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus.BAD_REQUEST

@ResponseStatus(BAD_REQUEST)
class BadRequestException(message: String) : RuntimeException(message)

