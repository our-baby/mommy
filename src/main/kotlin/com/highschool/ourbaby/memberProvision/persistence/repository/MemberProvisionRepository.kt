package com.highschool.ourbaby.memberProvision.persistence.repository

import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberProvisionRepository : JpaRepository<MemberProvisionEntity, Long> {
	@Query("SELECT mp FROM MemberProvisionEntity mp JOIN FETCH mp.provision p WHERE mp.member.id = :memberId")
	fun findProvisionsByMemberId(@Param("memberId") memberId: Long): List<MemberProvisionEntity>

	fun deleteByMemberId(memberId: Long)
}
