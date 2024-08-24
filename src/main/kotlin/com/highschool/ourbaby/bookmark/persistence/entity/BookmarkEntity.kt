package com.highschool.ourbaby.bookmark.persistence.entity

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.core.persistence.entity.BaseEntity
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "bookmark")
class BookmarkEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    val article: ArticleEntity,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    val member: MemberEntity,
) : BaseEntity()
