package com.highschool.ourbaby.memberProvision.service

import com.highschool.ourbaby.member.service.MemberService
import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import com.highschool.ourbaby.memberProvision.persistence.repository.MemberProvisionRepository
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.service.ProvisionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

	@Transactional
	fun deleteMemberProvision(memberId: Long, provisionId: Long) =
		memberProvisionRepository.deleteByMemberIdAndProvisionId(memberId, provisionId)
}
