package com.highschool.ourbaby.bookmark.persistence.repository

import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BookmarkRepository : JpaRepository<BookmarkEntity, Long> {
    fun deleteByArticleIdAndMemberId(
        articleId: Long,
        memberId: Long,
    )

    @Query("SELECT bm FROM BookmarkEntity bm JOIN FETCH bm.article WHERE bm.member.id = :memberId")
    fun findArticlesByMemberId(
        @Param("memberId") memberId: Long,
    ): List<BookmarkEntity>
}
