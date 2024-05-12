package com.highschool.ourbaby.provision.dto

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity

data class ProvisionRequestDto(
	val description: String,
) {
	fun toEntity() = ProvisionEntity(
		description = this.description,
	)
}
