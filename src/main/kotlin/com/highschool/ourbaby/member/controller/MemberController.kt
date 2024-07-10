package com.highschool.ourbaby.member.controller

import com.highschool.ourbaby.member.dto.MemberRequestDto
import com.highschool.ourbaby.member.dto.MemberResponseDto
import com.highschool.ourbaby.member.dto.SignInResponseDto
import com.highschool.ourbaby.member.service.MemberService
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
    fun getAuthenticationToken(@RequestHeader("certificated-token") certificatedToken: String) =
        SignInResponseDto(memberService.getAuthenticationToken(certificatedToken))

    @GetMapping
    fun getAllMembers(): List<MemberResponseDto> =
        memberService.getAllMembers().map { MemberResponseDto(it) }

    @GetMapping("/{id}")
    fun getMemberById(@PathVariable id: Long) = MemberResponseDto(memberService.getMemberById(id))

    @PostMapping
    fun createMember(@RequestBody memberRequestDto: MemberRequestDto) =
        MemberResponseDto(memberService.createMember(memberRequestDto.toEntity()))

    @PutMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody memberRequestDto: MemberRequestDto,
    ) = MemberResponseDto(memberService.updateMember(id, memberRequestDto.toEntity()))

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) = memberService.deleteMember(id)
}
