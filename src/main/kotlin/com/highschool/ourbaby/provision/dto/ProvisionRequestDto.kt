package com.highschool.ourbaby.provision.dto

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity

data class ProvisionRequestDto(
	val id: Long,
	val description: String,
) {
	fun toEntity() = ProvisionEntity(
		id = this.id,
		description = this.description,
	)
}
