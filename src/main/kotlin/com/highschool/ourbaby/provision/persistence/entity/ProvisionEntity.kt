package com.highschool.ourbaby.provision.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.provision.dto.ProvisionResponseDto
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
	val id: Long,
	@Column(columnDefinition = "TEXT")
	var description: String,
) : BaseEntity() {
	fun toDto() = ProvisionResponseDto(
		id = this.id,
		description = this.description,
		createdAt = this.createdAt,
		updatedAt = this.updatedAt,
	)
}
