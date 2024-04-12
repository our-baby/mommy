package com.highschool.ourbaby.tag.persistence.repository

import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<TagEntity, Long> {
}
