package com.jaewoo.srs.common.code.repository

import com.jaewoo.srs.common.code.domain.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.domain.entity.GroupCode
import com.jaewoo.srs.common.code.domain.entity.QGroupCode
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

interface GroupCodeRepositoryCustom {
    fun findAllPage(dto: SearchGroupCodeRequest, pageable: Pageable): Page<GroupCode>
}

@Repository
class GroupCodeRepositoryImpl(
    val query: JPAQueryFactory
) : GroupCodeRepositoryCustom {

    override fun findAllPage(dto: SearchGroupCodeRequest, pageable: Pageable): Page<GroupCode> {
        val groupCode = QGroupCode.groupCode1
        val queryResult = query.selectFrom(groupCode)
            .where(
                containName(dto.name)
            )
            .orderBy(groupCode.groupCode.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetchResults()

        return PageImpl(queryResult.results, pageable, queryResult.total)
    }

    fun containName(name: String?): BooleanExpression? {
        val groupCode = QGroupCode.groupCode1
        return name?.let {
            groupCode.groupCodeNameKo.contains(name)
                .or(groupCode.groupCodeNameEn.contains(name))
        }
    }
}