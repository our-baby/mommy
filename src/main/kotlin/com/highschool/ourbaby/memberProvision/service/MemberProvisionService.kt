package com.highschool.ourbaby.memberProvision.service

import com.highschool.ourbaby.member.service.MemberService
import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import com.highschool.ourbaby.memberProvision.persistence.repository.MemberProvisionRepository
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.service.ProvisionService
import org.springframework.stereotype.Service

@Service
class MemberProvisionService(
	private val memberService: MemberService,
	private val provisionService: ProvisionService,
	private val memberProvisionRepository: MemberProvisionRepository,
) {
	fun getProvisionsByMemberId(id: Long): List<ProvisionEntity> {
		return memberProvisionRepository.findProvisionsByMemberId(id).map { it.provision }
	}

	fun createMemberProvision(memberId: Long, provisionId: Long): MemberProvisionEntity {
		val member = memberService.getMemberById(memberId)
		val provision = provisionService.getProvisionById(provisionId)
		return memberProvisionRepository.save(MemberProvisionEntity(member = member, provision = provision))
	}

	fun deleteByMemberId(id: Long) = memberProvisionRepository.deleteByMemberId(id)
}
