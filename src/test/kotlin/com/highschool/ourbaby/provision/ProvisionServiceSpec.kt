package com.highschool.ourbaby.provision

import com.highschool.ourbaby.Mock
import com.highschool.ourbaby.SpringDataConfig
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.persistence.repository.ProvisionRepository
import com.highschool.ourbaby.provision.service.ProvisionService
import io.kotest.core.spec.style.ExpectSpec
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
class ProvisionServiceSpec(
	private val provisionRepository: ProvisionRepository
) : ExpectSpec() {
	private val provisionService = ProvisionService(provisionRepository)

	init {
		context("새로운 조항 생성할 때") {
			expect("입력한 정보 그대로 조항이 생성된다") {
				val provision = Mock.provision()
				val newProvision = createProvision(provision)
				validate(newProvision, provision)
			}
		}
		context("조항 수정할 때") {
			expect("입력한 정보 그대로 조항이 수정된다.") {
				val provision = Mock.provision()
				var newProvision = createProvision(provision)
				val updatedProvision = Mock.provision()
				newProvision = provisionService.updateProvision(newProvision.id, updatedProvision)
				validate(newProvision, updatedProvision)
			}
		}
		context("조항 조회할 때") {
			expect("생성한 조항이 그대로 조회된다.") {
				val provision = Mock.provision()
				createProvision(provision)
				val provisions = provisionService.getAllProvisions()
				provisions.size shouldBe 1
				validate(provisions[0], provision)
			}
		}
		context("특정 조항 조회할 때") {
			expect("생성한 조항이 그대로 조회된다.") {
				val provision = Mock.provision()
				val newProvision = createProvision(provision)
				val viewed = provisionService.getProvisionById(newProvision.id)
				validate(viewed, newProvision)
			}
		}
		context("조항 삭제할 때") {
			expect("원하는 조항이 정상적으로 삭제된다.") {
				val provision = Mock.provision()
				val newProvision = createProvision(provision)
				provisionService.deleteProvision(newProvision.id)
			}
		}
	}

	private fun createProvision(provision: ProvisionEntity) = provisionService.createProvision(provision)

	private fun validate(from: ProvisionEntity, to: ProvisionEntity) {
		from.description shouldBe to.description
		from.createdAt shouldNotBe null
		from.updatedAt shouldNotBe null
	}
}
