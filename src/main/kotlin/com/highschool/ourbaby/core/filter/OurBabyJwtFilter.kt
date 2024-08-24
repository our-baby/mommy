package com.highschool.ourbaby.core.filter

import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.service.JwtService
import com.highschool.ourbaby.member.service.MemberService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.OncePerRequestFilter

class OurBabyJwtFilter(
    private val jwtService: JwtService,
    private val memberService: MemberService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
//        if (request.requestURI.equals("/test/hello") || request.requestURI.equals("/api/members/sign-in")) {
//            filterChain.doFilter(request, response)
//        }
        extractAccessToken(request)
            ?.let {
                authenticateUserFromAccessToken(request, response, filterChain, it)
            }
            ?: filterChain.doFilter(request, response)
    }

    private fun authenticateUserFromAccessToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
        accessToken: String,
    ) {
        val userId = jwtService.extractClaims(accessToken).id.toLong()

        memberService
            .getMemberById(userId)
            .also {
                val authentication = getAuthentication(it)

                SecurityContextHolder.getContext().authentication = authentication
            }

        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(it: MemberEntity) =
        UsernamePasswordAuthenticationToken(
            User
                .builder()
                .username(it.id.toString())
                .password("password")
                .roles("USER")
                .build(),
            "credentials",
            listOf(SimpleGrantedAuthority("ROLE_USER")),
        )

    private fun extractAccessToken(request: HttpServletRequest): String? = request.getHeader("Authorization")
}
