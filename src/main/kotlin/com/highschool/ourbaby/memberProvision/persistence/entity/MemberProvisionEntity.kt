package com.highschool.ourbaby.memberProvision.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "member_provision")
class MemberProvisionEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    val member: MemberEntity,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "provision_id")
    val provision: ProvisionEntity,
) : BaseEntity()
