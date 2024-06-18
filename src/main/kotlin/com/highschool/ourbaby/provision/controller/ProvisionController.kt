package com.highschool.ourbaby.provision.controller

import com.highschool.ourbaby.provision.dto.ProvisionRequestDto
import com.highschool.ourbaby.provision.dto.ProvisionResponseDto
import com.highschool.ourbaby.provision.service.ProvisionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/provisions")
class ProvisionController(
	private val provisionService: ProvisionService,
) {
	@GetMapping
	fun getAllProvisions() = provisionService.getAllProvisions().map { ProvisionResponseDto(it) }

	@GetMapping("/{id}")
	fun getProvisionById(@PathVariable id: Long) =
		ProvisionResponseDto(provisionService.getProvisionById(id))

	@PostMapping
	fun createProvision(@RequestBody provisionRequestDto: ProvisionRequestDto) =
		ProvisionResponseDto(provisionService.createProvision(provisionRequestDto.toEntity()))

	@PutMapping("/{id}")
	fun updateProvision(
		@PathVariable(name = "id", required = true) id: Long,
		@RequestBody provisionRequestDto: ProvisionRequestDto
	) = ProvisionResponseDto(provisionService.updateProvision(id, provisionRequestDto.toEntity()))

	@DeleteMapping("/{id}")
	fun deleteProvision(@PathVariable id: Long) =
		provisionService.deleteProvision(id)
}
