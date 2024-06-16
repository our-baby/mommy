package com.highschool.ourbaby.member.persistence.repository

import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByNameAndEmail(name: String, email: String): MemberEntity?
}
