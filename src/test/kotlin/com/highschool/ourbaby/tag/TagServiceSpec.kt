package com.highschool.ourbaby.tag

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.tag.persistence.entity.TagEntity
import com.highschool.ourbaby.tag.persistence.repository.TagRepository
import com.highschool.ourbaby.tag.service.TagService
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.ContextConfiguration

@EnableJpaAuditing
@DataJpaTest
@ContextConfiguration(classes = [SpringDataConfig::class])
@AutoConfigureTestDatabase(replace = NONE)
class TagServiceSpec(private val tagRepository: TagRepository): ExpectSpec() {
	private val tagService = TagService(tagRepository)

	init {
		context("태그 생성할 때") {
			expect("입력한 정보 그대로 태그가 생성된다.") {
				val tag = Mock.tag()
				val newTag = createNewTag(tag)
				validate(tag, newTag)
			}
		}
		context("태그 조회할 때") {
			expect("태그 생성한 후 전체 조회할 때 똑같이 보여진다.") {
				val tag = Mock.tag()
				createNewTag(tag)
				val tags = tagService.getAllTags()
				tags.size shouldBeGreaterThan 0
			}
		}
		context("특정 태그 조회할 때") {
			expect("방금 생성한 태그가 그대로 보여진다.") {
				val tag = Mock.tag()
				val newTag = createNewTag(tag)
				val viewed = tagService.getTagById(newTag.id)
				validate(newTag, viewed)
			}
		}
		context("특정 태그 수정할 때") {
			expect("입력한 정보 그대로 수정된다.") {
				val tag = Mock.tag()
				var newTag = createNewTag(tag)
				val updatedTag = Mock.tag()
				newTag = tagService.updateTag(newTag.id, updatedTag)
				validate(newTag, updatedTag)
			}
		}
		context("특정 태그 삭제할 때") {
			expect("선택된 태그를 반환하면서 삭제된다.") {
				val tag = Mock.tag()
				val newTag = createNewTag(tag)
				tagService.deleteTag(newTag.id)
			}
		}
	}

	fun createNewTag(tag: TagEntity) = tagService.createTag(tag)

	fun validate(from: TagEntity, to: TagEntity) {
		from.name shouldBe to.name
		from.createdAt shouldNotBe null
		from.updatedAt shouldNotBe null
	}
}
