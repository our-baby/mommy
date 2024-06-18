package com.highschool.ourbaby

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackages = ["com.highschool.ourbaby"])
@EnableJpaRepositories(basePackages = ["com.highschool.ourbaby"])
class SpringDataConfig: AbstractProjectConfig() {
	override fun extensions() = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))
}
