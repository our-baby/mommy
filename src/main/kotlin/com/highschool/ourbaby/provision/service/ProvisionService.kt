package com.highschool.ourbaby.provision.service

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.persistence.repository.ProvisionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class ProvisionService(private val provisionRepository: ProvisionRepository) {
	fun getAllProvisions(): List<ProvisionEntity> = provisionRepository.findAll()

	fun getProvisionById(id: Long) =
		provisionRepository.findById(id).getOrNull() ?: throw NoSuchElementException("No Provision with id $id")

	@Transactional
	fun createProvision(incoming: ProvisionEntity) = provisionRepository.save(incoming)

	@Transactional
	fun updateProvision(id: Long, incoming: ProvisionEntity): ProvisionEntity {
		val origin = getProvisionById(id)
		return provisionRepository.save(
			ProvisionEntity(
				id = origin.id,
				description = incoming.description,
			)
		)
	}

	@Transactional
	fun deleteProvision(id: Long) {
		provisionRepository.deleteById(id)
	}
}
