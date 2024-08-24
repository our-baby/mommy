package com.highschool.ourbaby.category.controller

import com.highschool.ourbaby.category.dto.CategoryRequestDto
import com.highschool.ourbaby.category.dto.CategoryResponseDto
import com.highschool.ourbaby.category.service.CategoryService
import com.highschool.ourbaby.core.response.ApiResponse
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
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService,
) {
    @GetMapping
    fun getAllTags() = ResponseEntity.ok(ApiResponse(categoryService.getAllCategories().map { CategoryResponseDto(it) }))

    @GetMapping("/{id}")
    fun getTagById(
        @PathVariable(name = "id", required = true) id: Long,
    ) = ResponseEntity.ok(ApiResponse(CategoryResponseDto(categoryService.getCategoryById(id))))

    @PostMapping
    fun createTag(
        @RequestBody categoryRequestDto: CategoryRequestDto,
    ) = ResponseEntity.ok(ApiResponse(CategoryResponseDto(categoryService.createCategory(categoryRequestDto.toEntity()))))

    @PutMapping("/{id}")
    fun updateTag(
        @PathVariable id: Long,
        @RequestBody categoryRequestDto: CategoryRequestDto,
    ) = ResponseEntity.ok(ApiResponse(CategoryResponseDto(categoryService.updateCategory(id, categoryRequestDto.toEntity()))))

    @DeleteMapping("/{id}")
    fun deleteTag(
        @PathVariable id: Long,
    ) = ResponseEntity.ok(ApiResponse(categoryService.deleteCategory(id)))
}
