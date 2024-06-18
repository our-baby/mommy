package com.highschool.ourbaby.provision.dto

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity

data class ProvisionResponseDto(
	val id: Long,
	val description: String,
) {
	constructor(provisionEntity: ProvisionEntity): this(
		provisionEntity.id,
		provisionEntity.description,
	)
}
