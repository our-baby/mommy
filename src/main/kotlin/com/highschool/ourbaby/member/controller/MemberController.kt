package com.highschool.ourbaby.member.controller

import com.highschool.ourbaby.member.dto.MemberResponseDto
import com.highschool.ourbaby.member.dto.SignInResponseDto
import com.highschool.ourbaby.member.dto.UpdateMemberRequestDto
import com.highschool.ourbaby.member.service.MemberService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("sign-in")
    fun getAuthenticationToken(
        @RequestHeader("certificated-token") certificatedToken: String,
    ): SignInResponseDto {
        val token = memberService.getAuthenticationToken(certificatedToken)

        return SignInResponseDto(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
        )
    }

    @GetMapping
    fun getMember(
        @AuthenticationPrincipal
        user: User,
    ) = MemberResponseDto(memberService.getMemberById(user.username.toLong()))

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody updateMemberRequestDto: UpdateMemberRequestDto,
    ) = MemberResponseDto(memberService.updateMember(id, updateMemberRequestDto.toDomain()))

    @DeleteMapping("/{id}")
    fun deleteMember(
        @PathVariable id: Long,
    ) = memberService.deleteMember(id)
}
