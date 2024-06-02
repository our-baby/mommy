package com.highschool.ourbaby.core.config

import com.highschool.ourbaby.core.filter.OurBabyJwtFilter
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import com.highschool.ourbaby.oauth2.service.CustomOAuth2UserService
import com.highschool.ourbaby.member.service.JwtService
import com.highschool.ourbaby.oauth2.service.handler.OAuth2LoginFailureHandler
import com.highschool.ourbaby.oauth2.service.handler.OAuth2LoginSuccessHandler
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
    private val successHandler: OAuth2LoginSuccessHandler,

    private val failureHandler: OAuth2LoginFailureHandler,

    private val customOAuth2UserService: CustomOAuth2UserService,

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
                it.requestMatchers("/","/css/**","/images/**","/js/**","/favicon.ico").permitAll()
                it.requestMatchers("/").permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterAfter(ourBabyJwtFilter(), LogoutFilter::class.java)

        // 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
        // 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
        // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter


        return http.build()
    }

    @Bean
    fun ourBabyJwtFilter() = OurBabyJwtFilter(jwtService, memberRepository)
}