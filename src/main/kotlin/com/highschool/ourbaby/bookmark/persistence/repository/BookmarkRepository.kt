package com.highschool.ourbaby.bookmark.persistence.repository

import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface BookmarkRepository : JpaRepository<BookmarkEntity, Long> {
	@Transactional
	// TODO: deleteByArticleIdAndMember -> deleteByArticleIdAndMemberId
	fun deleteByArticleIdAndMember(articleId: Long, memberId: Long)

	// TODO: fetch join for member
	//  @Query("SELECT bm FROM BookmarkEntity bm JOIN FETCH bm.article WHERE bm.member.id = :memberId)
	//  fun findArticlesByMemberId(@Param("memberId") memberId: Long): List<BookmarkEntity>
}