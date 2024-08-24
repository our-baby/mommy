package com.highschool.ourbaby.member.service

import com.highschool.ourbaby.core.util.makeRandomString
import com.highschool.ourbaby.member.domain.JoinType.NAVER
import com.highschool.ourbaby.member.domain.UpdateMember
import com.highschool.ourbaby.member.domain.oauth.Token
import com.highschool.ourbaby.member.external.feign.oauth.NaverOAuthFeign
import com.highschool.ourbaby.member.persistence.entity.MemberEntity
import com.highschool.ourbaby.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Service
import java.util.Date
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

    fun getAuthenticationToken(authCode: String): Token {
        val naverUser = naverOAuthFeign.getNaverUserInfo(authCode).toDomain()

        val member =
            memberRepository.findByNameAndEmail(naverUser.name, naverUser.email)
                ?: createMember(
                    MemberEntity(
                        email = naverUser.email,
                        name = naverUser.name,
                        nickname = makeRandomString(NICKNAME_LENGTH),
                        joinType = NAVER,
                        profileImage = naverUser.profileImage,
                    ),
                )

        val now = Date()

        return Token(
            accessToken = jwtService.createAccessToken(now, member.id),
            refreshToken = jwtService.createRefreshToken(now, member.id),
        )
    }

    fun getMemberById(id: Long): MemberEntity = memberRepository.findById(id).getOrElse { throw NoSuchElementException("존재하지 않는 유저입니다.") }

    fun createMember(incoming: MemberEntity) = memberRepository.save(incoming)

    fun updateMember(
        id: Long,
        updateMember: UpdateMember,
    ): MemberEntity {
        val member = getMemberById(id)
        val memberEntity =
            MemberEntity(
                id = member.id,
                email = updateMember.email,
                name = updateMember.name,
                nickname = updateMember.nickname,
                joinType = updateMember.joinType,
                profileImage = member.profileImage,
            )

        return memberRepository.save(memberEntity)
    }

    fun deleteMember(id: Long) = memberRepository.deleteById(id)
}
