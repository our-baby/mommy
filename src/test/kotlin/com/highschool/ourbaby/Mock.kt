package com.highschool.ourbaby

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*

object Mock {
	fun article(): ArticleEntity = ArticleEntity(
		id = 0,
		title = Arb.string(5..50).single(),
		summary = Arb.string(5..200).single(),
		link = Arb.stringPattern("(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?\\/[a-zA-Z0-9]{2,}\n")
			.single(),
		menuTag = Arb.string(3..10).single(),
		hits = Arb.int(1..10).single(),
		linkHits = Arb.int(0..10).single(),
		isPublished = Arb.boolean().single(),
	)
}
