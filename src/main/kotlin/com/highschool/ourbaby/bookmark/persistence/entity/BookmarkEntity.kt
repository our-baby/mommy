package com.highschool.ourbaby.bookmark.persistence.entity

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "bookmark")
class BookmarkEntity(
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bookmark_id")
	val id: Long = 0,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	val article: ArticleEntity,

	// TODO: @ManyToOne(fetch = FetchType.LAZY)
	// TODO: JoinColumn(name = "member_id")
	@Column(name = "member_id")
	val member: Long = 0, // TODO: Long -> MemberEntity
) : BaseEntity()