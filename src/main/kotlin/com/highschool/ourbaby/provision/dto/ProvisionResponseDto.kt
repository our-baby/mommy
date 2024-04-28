package com.highschool.ourbaby.provision.dto

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import java.time.LocalDateTime

data class ProvisionResponseDto(
	val id: Long,
	val description: String,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
) {
	companion object Factory {
		fun fromEntity(provisionEntity: ProvisionEntity) = ProvisionResponseDto(
			id = provisionEntity.id,
			description = provisionEntity.description,
			createdAt = provisionEntity.createdAt,
			updatedAt = provisionEntity.updatedAt,
		)
	}
}
