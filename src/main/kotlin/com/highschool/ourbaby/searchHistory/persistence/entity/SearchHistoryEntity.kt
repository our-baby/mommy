package com.highschool.ourbaby.searchHistory.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "search_history")
class SearchHistoryEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,
    @Column(name = "keyword", nullable = false)
    val keyword: String,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    val member: MemberEntity,
) : BaseEntity()
