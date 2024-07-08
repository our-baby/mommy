package com.highschool.ourbaby.memberProvision.controller

import com.highschool.ourbaby.memberProvision.dto.MemberProvisionRequestDto
import com.highschool.ourbaby.memberProvision.dto.MemberProvisionResponseDto
import com.highschool.ourbaby.memberProvision.service.MemberProvisionService
import com.highschool.ourbaby.provision.dto.ProvisionResponseDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/memberProvisions")
class MemberProvisionController(
	private val memberProvisionService: MemberProvisionService,
) {
	@GetMapping("/{id}")
	fun getProvisionsByMemberId(@PathVariable id: Long) =
		memberProvisionService.getProvisionsByMemberId(id).map { ProvisionResponseDto(it) }

	@PostMapping
	fun createMemberProvision(@RequestBody memberProvisionRequestDto: MemberProvisionRequestDto) =
		MemberProvisionResponseDto(
			memberProvisionService.createMemberProvision(
				memberProvisionRequestDto.memberId,
				memberProvisionRequestDto.provisionId
			)
		)

	@DeleteMapping
	fun deleteMemberProvision(@RequestParam memberId: Long, @RequestParam provisionId: Long) =
		memberProvisionService.deleteMemberProvision(memberId, provisionId)
}
