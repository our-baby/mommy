package com.highschool.ourbaby

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.member.domain.JoinType.NAVER
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*

object Mock {
    fun article() =
        ArticleEntity(
            id = 0,
            title = Arb.string(5..50).single(),
            summary = Arb.string(5..200).single(),
            link =
                Arb
                    .stringPattern(
                        "(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?\\/[a-zA-Z0-9]{2,}\n",
                    ).single(),
            hits = Arb.int(1..10).single(),
            linkHits = Arb.int(0..10).single(),
            isPublished = Arb.boolean().single(),
            category = category(),
        )

    fun category() =
        CategoryEntity(
            id = 0,
            name = Arb.string(2..10).single(),
        )

    fun provision() =
        ProvisionEntity(
            id = 0,
            description = Arb.string(minSize = 5, maxSize = 100).single(),
        )

    fun member() =
        MemberEntity(
            id = 0,
            email = "example@example.com",
            name = Arb.string(5..10).single(),
            nickname = Arb.string(5..10).single(),
            joinType = NAVER,
        )

    fun searchHistory() =
        SearchHistoryEntity(
            id = 0,
            member = member(),
            keyword = Arb.string(5..10).single(),
        )
}
