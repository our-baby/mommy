package com.highschool.ourbaby.core.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.highschool.ourbaby.core.exception.ErrorCode
import com.highschool.ourbaby.core.exception.ErrorCode.TOKEN_INVALID
import com.highschool.ourbaby.core.exception.ErrorCode.UNKNOWN_ERROR
import com.highschool.ourbaby.core.exception.TokenInvalidException
import com.highschool.ourbaby.core.exception.dto.ExceptionResponseDto
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionHandlerFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (ex: TokenInvalidException) {
            setErrorResponse(response, TOKEN_INVALID)
        } catch (ex: Exception) {
            setErrorResponse(response, UNKNOWN_ERROR)
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        errorCode: ErrorCode,
    ) {
        val objectMapper = jacksonObjectMapper()
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        val errorResponse = ExceptionResponseDto(_errorCode = errorCode, message = errorCode.message)

        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
