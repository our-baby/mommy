package com.highschool.ourbaby.category

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.category.persistence.repository.CategoryRepository
import com.highschool.ourbaby.category.service.CategoryService
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
class CategoryServiceSpec(private val categoryRepository: CategoryRepository): ExpectSpec() {
	private val categoryService = CategoryService(categoryRepository)

	init {
		context("태그 생성할 때") {
			expect("입력한 정보 그대로 태그가 생성된다.") {
				val tag = Mock.category()
				val newTag = createNewTag(tag)
				validate(tag, newTag)
			}
		}
		context("태그 조회할 때") {
			expect("태그 생성한 후 전체 조회할 때 똑같이 보여진다.") {
				val tag = Mock.category()
				createNewTag(tag)
				val tags = categoryService.getAllCategories()
				tags.size shouldBeGreaterThan 0
			}
		}
		context("특정 태그 조회할 때") {
			expect("방금 생성한 태그가 그대로 보여진다.") {
				val tag = Mock.category()
				val newTag = createNewTag(tag)
				val viewed = categoryService.getCategoryById(newTag.id)
				validate(newTag, viewed)
			}
		}
		context("특정 태그 수정할 때") {
			expect("입력한 정보 그대로 수정된다.") {
				val tag = Mock.category()
				var newTag = createNewTag(tag)
				val updatedTag = Mock.category()
				newTag = categoryService.updateCategory(newTag.id, updatedTag)
				validate(newTag, updatedTag)
			}
		}
		context("특정 태그 삭제할 때") {
			expect("선택된 태그를 반환하면서 삭제된다.") {
				val tag = Mock.category()
				val newTag = createNewTag(tag)
				categoryService.deleteCategory(newTag.id)
			}
		}
	}

	fun createNewTag(tag: CategoryEntity) = categoryService.createCategory(tag)

	fun validate(from: CategoryEntity, to: CategoryEntity) {
		from.name shouldBe to.name
		from.createdAt shouldNotBe null
		from.updatedAt shouldNotBe null
	}
}
