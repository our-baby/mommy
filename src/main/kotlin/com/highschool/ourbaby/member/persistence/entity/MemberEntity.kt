package com.highschool.ourbaby.member.persistence.entity

import com.highschool.ourbaby.member.domain.JoinType
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val nickname: String,

    @Enumerated(value = STRING)
    val joinType: JoinType,
)
