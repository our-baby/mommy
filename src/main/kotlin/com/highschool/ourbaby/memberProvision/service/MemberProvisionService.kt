package com.highschool.ourbaby.memberProvision.service

import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import com.highschool.ourbaby.memberProvision.persistence.repository.MemberProvisionRepository
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.service.ProvisionService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class MemberProvisionService(
	private val memberRepository: MemberRepository,
	private val provisionService: ProvisionService,
	private val memberProvisionRepository: MemberProvisionRepository,
) {
	fun getProvisionsByMemberId(id: Long): List<ProvisionEntity> {
		return memberProvisionRepository.findProvisionsByMemberId(id).map { it.provision }
	}

	fun createMemberProvision(memberId: Long, provisionId: Long): MemberProvisionEntity {
		val member = memberRepository.findById(memberId).getOrElse { throw NoSuchElementException("존재하느 않는 유저입니다.") }
		val provision = provisionService.getProvisionById(provisionId)
		return memberProvisionRepository.save(MemberProvisionEntity(member = member, provision = provision))
	}

	fun deleteByMemberId(id: Long) = memberProvisionRepository.deleteByMemberId(id)
}
