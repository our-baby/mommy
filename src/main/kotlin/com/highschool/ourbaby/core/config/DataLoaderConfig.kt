package com.highschool.ourbaby.core.config

import com.highschool.ourbaby.article.persistence.entity.ArticleEntity
import com.highschool.ourbaby.article.persistence.repository.ArticleRepository
import com.highschool.ourbaby.bookmark.persistence.entity.BookmarkEntity
import com.highschool.ourbaby.bookmark.persistence.repository.BookmarkRepository
import com.highschool.ourbaby.category.persistence.entity.CategoryEntity
import com.highschool.ourbaby.category.persistence.repository.CategoryRepository
import com.highschool.ourbaby.member.domain.JoinType
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import com.highschool.ourbaby.memberProvision.persistence.entity.MemberProvisionEntity
import com.highschool.ourbaby.memberProvision.persistence.repository.MemberProvisionRepository
import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import com.highschool.ourbaby.provision.persistence.repository.ProvisionRepository
import com.highschool.ourbaby.searchHistory.persistence.entity.SearchHistoryEntity
import com.highschool.ourbaby.searchHistory.persistence.repository.SearchHistoryRepository
import io.github.serpro69.kfaker.Faker
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoaderConfig(
    private val categoryRepository: CategoryRepository,
    private val articleRepository: ArticleRepository,
    private val memberRepository: MemberRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val provisionRepository: ProvisionRepository,
    private val memberProvisionRepository: MemberProvisionRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
) : CommandLineRunner {
    val faker = Faker()

    override fun run(vararg args: String?) {
        this.createCategories()
        this.createArticles()
        this.createMembers()
        this.createBookmarks()
        this.createProvisions()
        this.createMemberProvisions()
        this.createSearchHistories()
    }

    fun createCategories() {
        if (categoryRepository.count() > 0L) {
            return
        }
        val parenting = CategoryEntity(name = "육아")
        val psychology = CategoryEntity(name = "심리")
        categoryRepository.saveAll(listOf(parenting, psychology))
    }

    fun createArticles() {
        if (categoryRepository.count() == 0L || articleRepository.count() > 0L) {
            return
        }
        val categories = categoryRepository.findAll()
        val parenting =
            categories.filter { it.name == "육아" }.getOrNull(0) ?: throw NoSuchElementException("존재하지 않는 카테고리입니다.")
        val psychology =
            categories.filter { it.name == "심리" }.getOrNull(0) ?: throw NoSuchElementException("존재하지 않는 카테고리입니다.")
        val articles =
            listOf(
                ArticleEntity(
                    title = "아이 발달 이해하기",
                    summary = "아이의 발달 단계에 대한 글입니다.",
                    link = "https://example.com/child-development",
                    hits = 100,
                    linkHits = 50,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "유아기 분노 조절 방법",
                    summary = "아이의 분노를 효과적으로 관리하는 팁입니다.",
                    link = "https://example.com/toddler-tantrums",
                    hits = 200,
                    linkHits = 80,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "유아 심리학",
                    summary = "유아기 심리학에 대한 이해.",
                    link = "https://example.com/early-childhood",
                    hits = 150,
                    linkHits = 60,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "감정 지능 키우기",
                    summary = "감정적으로 지능이 높은 아이를 키우는 가이드.",
                    link = "https://example.com/emotional-intelligence",
                    hits = 120,
                    linkHits = 70,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "부모를 위한 셀프케어",
                    summary = "바쁜 부모를 위한 셀프케어 아이디어.",
                    link = "https://example.com/self-care-parents",
                    hits = 80,
                    linkHits = 30,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "아동 심리 이해하기",
                    summary = "아동 심리에 대한 개요.",
                    link = "https://example.com/child-psychology",
                    hits = 300,
                    linkHits = 150,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "아동 불안 관리하기",
                    summary = "아이들이 불안을 관리할 수 있도록 돕는 방법.",
                    link = "https://example.com/child-anxiety",
                    hits = 250,
                    linkHits = 120,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "아이의 정신적 웰빙 촉진하기",
                    summary = "정신적 웰빙을 촉진하는 전략.",
                    link = "https://example.com/mental-wellbeing",
                    hits = 220,
                    linkHits = 110,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "학습 심리학",
                    summary = "아이들이 놀이와 상호작용을 통해 어떻게 배우는지.",
                    link = "https://example.com/learning-psychology",
                    hits = 210,
                    linkHits = 90,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "회복력 있는 아이로 키우기",
                    summary = "아이의 회복력을 키우는 가이드.",
                    link = "https://example.com/resilient-children",
                    hits = 190,
                    linkHits = 100,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "아이의 긍정적인 행동 개발하기",
                    summary = "초기 발달 단계에서 긍정적인 행동을 장려하는 방법.",
                    link = "https://example.com/positive-behavior",
                    hits = 170,
                    linkHits = 80,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "형제 간 경쟁 관리하기",
                    summary = "형제 간 경쟁을 관리하는 기술.",
                    link = "https://example.com/sibling-rivalry",
                    hits = 160,
                    linkHits = 70,
                    isPublished = true,
                    category = parenting,
                ),
                ArticleEntity(
                    title = "유아기 애착 이론",
                    summary = "애착 이론을 탐구합니다.",
                    link = "https://example.com/attachment-theory",
                    hits = 180,
                    linkHits = 90,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "아이의 감정 조절 돕기",
                    summary = "아이들이 감정을 조절하도록 돕는 방법.",
                    link = "https://example.com/emotional-regulation",
                    hits = 240,
                    linkHits = 100,
                    isPublished = true,
                    category = psychology,
                ),
                ArticleEntity(
                    title = "놀이가 발달에 미치는 영향",
                    summary = "놀이가 아이의 성장과 발달에 미치는 영향.",
                    link = "https://example.com/importance-of-play",
                    hits = 230,
                    linkHits = 130,
                    isPublished = true,
                    category = parenting,
                ),
            )
        articleRepository.saveAll(articles)
    }

    fun createMembers() {
        if (memberRepository.count() > 0L) {
            return
        }
        val memberList =
            List(2) {
                // Generate a list of 10 mock members
                MemberEntity(
                    email = faker.internet.email(),
                    name = faker.name.name(),
                    nickname = faker.funnyName.name(),
                    joinType = JoinType.NAVER,
                    profileImage = faker.avatar.characters(),
                )
            }
        memberRepository.saveAll(memberList)
    }

    fun createBookmarks() {
        if (bookmarkRepository.count() > 0L) {
            return
        }
        val articleList = articleRepository.findAll()
        val memberList = memberRepository.findAll()
        val bookmarkList = articleList.map { it -> BookmarkEntity(article = it, member = memberList.random()) }
        bookmarkRepository.saveAll(bookmarkList)
    }

    fun createProvisions() {
        if (provisionRepository.count() > 0L) {
            return
        }
        val provisionList =
            List(1) {
                ProvisionEntity(description = faker.lorem.words())
            }
        provisionRepository.saveAll(provisionList)
    }

    fun createMemberProvisions() {
        if (memberProvisionRepository.count() > 0L) {
            return
        }
        val memberList = memberRepository.findAll()
        val provision = provisionRepository.findAll().get(0) ?: throw NoSuchElementException("존재하지 않는 카테고리입니다.")
        val memberProvisionList = memberList.map { it -> MemberProvisionEntity(member = it, provision = provision) }
        memberProvisionRepository.saveAll(memberProvisionList)
    }

    fun createSearchHistories() {
        if (searchHistoryRepository.count() > 0L) {
            return
        }
        val memberList = memberRepository.findAll()
        val searchHistoryList =
            memberList.map { it -> SearchHistoryEntity(member = memberList.random(), keyword = faker.lorem.words()) }
        searchHistoryRepository.saveAll(searchHistoryList)
    }
}
