package com.highschool.ourbaby.bookmark.persistence.repository

import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface BookmarkRepository : JpaRepository<BookmarkEntity, Long> {
	// TODO: deleteByArticleIdAndMember -> deleteByArticleIdAndMemberId
	fun deleteByArticleIdAndMember(articleId: Long, memberId: Long)

	@Query("SELECT bm FROM BookmarkEntity bm JOIN FETCH bm.article WHERE bm.member = :memberId")
	fun findArticlesByMemberId(@Param("memberId") memberId: Long): List<BookmarkEntity>
}
