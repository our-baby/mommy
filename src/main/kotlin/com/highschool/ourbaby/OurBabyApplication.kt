package com.highschool.ourbaby

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class OurBabyApplication

fun main(args: Array<String>) {
	runApplication<OurBabyApplication>(*args)
}
