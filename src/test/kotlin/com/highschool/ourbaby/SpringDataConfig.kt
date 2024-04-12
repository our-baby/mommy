package com.highschool.ourbaby

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles

@EntityScan(basePackages = ["com.highschool.ourbaby"])
@EnableJpaRepositories(basePackages = ["com.highschool.ourbaby"])
class SpringDataConfig: AbstractProjectConfig() {
	override fun extensions() = listOf(SpringExtension)
}
