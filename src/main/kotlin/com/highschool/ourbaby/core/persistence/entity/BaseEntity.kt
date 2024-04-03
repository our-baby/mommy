package com.highschool.ourbaby.core.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open abstract class BaseEntity {
	@Column(name = "created_at", updatable = false, columnDefinition = "DATETIME", nullable = false)
	@CreatedDate
	protected open var createdAt: LocalDateTime = LocalDateTime.now()

	@LastModifiedDate
	@Column(name = "updated_at", columnDefinition = "DATETIME")
	protected open var updatedAt: LocalDateTime? = null
}