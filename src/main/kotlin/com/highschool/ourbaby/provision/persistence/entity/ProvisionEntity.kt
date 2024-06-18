package com.highschool.ourbaby.provision.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "provision")
class ProvisionEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	val id: Long = 0,
	@Column(columnDefinition = "TEXT")
	val description: String,
) : BaseEntity()
