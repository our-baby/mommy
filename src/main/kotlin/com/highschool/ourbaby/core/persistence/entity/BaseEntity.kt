package com.highschool.ourbaby.core.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {
	@Column(name = "created_at", updatable = false, columnDefinition = "DATETIME", nullable = false)
	@CreatedDate
	open var createdAt: LocalDateTime = LocalDateTime.now()

	@Column(name = "updated_at", columnDefinition = "DATETIME")
	open var updatedAt: LocalDateTime = LocalDateTime.now()
}
