package com.highschool.ourbaby.memberProvision.dto

import com.highschool.ourbaby.member.dto.MemberResponseDto
import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import com.highschool.ourbaby.provision.dto.ProvisionResponseDto

data class MemberProvisionResponseDto(
	val id: Long,
	val member: MemberResponseDto,
	val provision: ProvisionResponseDto,
){
	constructor(memberProvisionEntity: MemberProvisionEntity): this(
		memberProvisionEntity.id,
		MemberResponseDto(memberProvisionEntity.member),
		ProvisionResponseDto(memberProvisionEntity.provision),
	)
}
