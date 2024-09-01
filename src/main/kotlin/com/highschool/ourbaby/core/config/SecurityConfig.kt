package com.highschool.ourbaby.core.config

import com.highschool.ourbaby.core.filter.ExceptionHandlerFilter
import com.highschool.ourbaby.core.filter.OurBabyJwtFilter
import com.highschool.ourbaby.member.service.JwtService
import com.highschool.ourbaby.member.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter

// @EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtService: JwtService,
    private val memberService: MemberService,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .headers { it.frameOptions { it.disable() } }
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico")
                    .permitAll()
                it.requestMatchers("/api/members/sign-in").permitAll()
                it.anyRequest().permitAll()
            }.addFilterAfter(ourBabyJwtFilter(), LogoutFilter::class.java)
            .addFilterBefore(exceptionHandlerFilter(), OurBabyJwtFilter::class.java)

        return http.build()
    }

    @Bean
    fun ourBabyJwtFilter() = OurBabyJwtFilter(jwtService, memberService)

    @Bean
    fun exceptionHandlerFilter() = ExceptionHandlerFilter()
}
