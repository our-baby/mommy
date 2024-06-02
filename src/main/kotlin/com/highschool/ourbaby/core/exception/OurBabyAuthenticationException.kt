package com.highschool.ourbaby.core.exception

import org.springframework.security.core.AuthenticationException

class OurBabyAuthenticationException(message: String, ex: Throwable): AuthenticationException(message, ex)
