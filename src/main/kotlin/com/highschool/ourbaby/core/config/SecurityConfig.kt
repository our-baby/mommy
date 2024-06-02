package com.highschool.ourbaby.core.config

import com.highschool.ourbaby.core.filter.OurBabyJwtFilter
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import com.highschool.ourbaby.member.service.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtService: JwtService,

    private val memberRepository: MemberRepository,
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
                it.requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico").permitAll()
                it.requestMatchers("/").permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterAfter(ourBabyJwtFilter(), LogoutFilter::class.java)

        return http.build()
    }

    @Bean
    fun ourBabyJwtFilter() = OurBabyJwtFilter(jwtService, memberRepository)
}