package com.highschool.ourbaby.provision.service

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.persistence.repository.ProvisionRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProvisionService(private val provisionRepository: ProvisionRepository) {
	fun getAllProvisions(): List<ProvisionEntity> = provisionRepository.findAll()

	fun getProvisionById(id: Long) =
		provisionRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Provision with id $id")

	fun createProvision(incoming: ProvisionEntity) = provisionRepository.save(incoming)

	fun updateProvision(id: Long, incoming: ProvisionEntity): ProvisionEntity {
		val origin = getProvisionById(id)
		val updated = updateProvisionProperties(origin, incoming)
		return provisionRepository.save(updated)
	}

	fun deleteProvision(id: Long): ProvisionEntity {
		val deleted = getProvisionById(id)
		provisionRepository.deleteById(id)
		return deleted
	}

	private fun updateProvisionProperties(origin: ProvisionEntity, incoming: ProvisionEntity): ProvisionEntity {
		origin.description = incoming.description
		return origin
	}
}