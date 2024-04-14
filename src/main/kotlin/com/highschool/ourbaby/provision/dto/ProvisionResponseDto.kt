package com.highschool.ourbaby.provision.dto

import java.time.LocalDateTime

data class ProvisionResponseDto(
	val id: Long,
	val description: String,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime?,
)
