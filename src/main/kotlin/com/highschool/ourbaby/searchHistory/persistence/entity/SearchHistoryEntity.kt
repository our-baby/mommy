package com.highschool.ourbaby.searchHistory.persistence.entity

import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "search_history")
class SearchHistoryEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "search_history_id")
	val id: Long = 0,

	@Column(name = "keyword", nullable = true)
	val keyword: String,

	// TODO: @ManyToOne(fetch = LAZY)
	// TODO: JoinColumn(name = "member_id")
	@Column(name = "member_id")
	val member: Long = 0, // TODO: Long -> MemeberEntity

) : BaseEntity() {}
