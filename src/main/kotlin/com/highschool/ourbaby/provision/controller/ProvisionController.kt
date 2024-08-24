package com.highschool.ourbaby.provision.controller

import com.highschool.ourbaby.core.response.ApiResponse
import com.highschool.ourbaby.provision.dto.ProvisionRequestDto
import com.highschool.ourbaby.provision.dto.ProvisionResponseDto
import com.highschool.ourbaby.provision.service.ProvisionService
import org.springframework.http.ResponseEntity
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
    fun getAllProvisions() = ResponseEntity.ok(ApiResponse(provisionService.getAllProvisions().map { ProvisionResponseDto(it) }))

    @GetMapping("/{id}")
    fun getProvisionById(
        @PathVariable id: Long,
    ) = ResponseEntity.ok(ApiResponse(ProvisionResponseDto(provisionService.getProvisionById(id))))

    @PostMapping
    fun createProvision(
        @RequestBody provisionRequestDto: ProvisionRequestDto,
    ) = ResponseEntity.ok(ApiResponse(ProvisionResponseDto(provisionService.createProvision(provisionRequestDto.toEntity()))))

    @PutMapping("/{id}")
    fun updateProvision(
        @PathVariable(name = "id", required = true) id: Long,
        @RequestBody provisionRequestDto: ProvisionRequestDto,
    ) = ResponseEntity.ok(ApiResponse(ProvisionResponseDto(provisionService.updateProvision(id, provisionRequestDto.toEntity()))))

    @DeleteMapping("/{id}")
    fun deleteProvision(
        @PathVariable id: Long,
    ) = ResponseEntity.ok(ApiResponse(provisionService.deleteProvision(id)))
}
