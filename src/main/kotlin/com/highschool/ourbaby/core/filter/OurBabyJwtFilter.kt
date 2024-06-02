package com.highschool.ourbaby.core.filter

import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import com.highschool.ourbaby.member.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.coyote.BadRequestException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.jvm.optionals.getOrElse

class OurBabyJwtFilter(
    private val jwtService: JwtService,

    private val memberRepository: MemberRepository,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.requestURI.equals("/test/**")) {
            filterChain.doFilter(request, response)
            return;
        }

        extractAccessToken(request)
            ?.let {
                authenticateUserFromAccessToken(request, response, filterChain, it)
            }
            ?: throw BadRequestException("사용자 로그인 토큰이 없습니다.")
    }

    private fun authenticateUserFromAccessToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
        accessToken: String
    ) {
        val userId = jwtService.extractClaims(accessToken).id.toLong()

        memberRepository.findById(userId).getOrElse { throw NoSuchElementException("존재하지 않는 유저입니다.") }
            .also {
                val authentication = getAuthentication(it)

                SecurityContextHolder.getContext().authentication = authentication
            }

        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(it: MemberEntity) =
        UsernamePasswordAuthenticationToken(
            User.builder()
                .username(it.id.toString())
                .roles("USER")
                .build(),
            null
        )

    private fun extractAccessToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }
}
