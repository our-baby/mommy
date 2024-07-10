package com.highschool.ourbaby.member.service

import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class MemberService(
	private val memberRepository: MemberRepository
) {

	fun getAllMembers(): List<MemberEntity> = memberRepository.findAll()

	fun getMemberById(id: Long): MemberEntity =
		memberRepository.findById(id).getOrElse { throw NoSuchElementException("존재하지 않는 유저입니다.") }

	fun createMember(incoming: MemberEntity) = memberRepository.save(incoming)

	fun updateMember(id: Long, incoming: MemberEntity): MemberEntity {
		val member = getMemberById(id)
		val update = MemberEntity(
			id = member.id,
			email = incoming.email,
			name = incoming.name,
			nickname = incoming.nickname,
			joinType = incoming.joinType,
		)
		return memberRepository.save(update)
	}

	fun deleteMember(id: Long) = memberRepository.deleteById(id)
}
