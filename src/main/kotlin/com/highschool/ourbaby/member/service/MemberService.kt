package com.highschool.ourbaby.member.service

import com.highschool.ourbaby.core.util.makeRandomString
import com.highschool.ourbaby.member.domain.JoinType.NAVER
import com.highschool.ourbaby.member.external.feign.oauth.NaverOAuthFeign
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val naverOAuthFeign: NaverOAuthFeign,
    private val jwtService: JwtService,
) {
    companion object {
        private val NICKNAME_LENGTH = 30
    }

    fun getAuthenticationToken(authCode: String): String {
        val bearerPrefixed = "Bearer " + authCode

        val naverUser = naverOAuthFeign.getNaverUserInfo(bearerPrefixed).toDomain()

        val member = memberRepository.findByNameAndEmail(naverUser.name, naverUser.email)
            ?: createMember(
                MemberEntity(
                    email = naverUser.email,
                    name = naverUser.name,
                    nickname = makeRandomString(NICKNAME_LENGTH),
                    joinType = NAVER,
                )
            )

        return jwtService.createAccessToken(member.id)
    }

    fun getAllMembers(): List<MemberEntity> = memberRepository.findAll()

    fun getMemberById(id: Long): MemberEntity =
        memberRepository.findById(id).getOrElse { throw NoSuchElementException("존재하지 않는 유저입니다.") }

    fun createMember(incoming: MemberEntity) = memberRepository.save(incoming)

    fun updateMember(id: Long, incoming: MemberEntity): MemberEntity {
        val member = getMemberById(id)
        val update = MemberEntity(
            id = member.id,
            email = incoming.email,
            name = incoming.name,
            nickname = incoming.nickname,
            joinType = incoming.joinType,
        )
        return memberRepository.save(update)
    }

    fun deleteMember(id: Long) = memberRepository.deleteById(id)
}
