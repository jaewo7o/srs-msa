package com.jaewoo.srs.common.code.repository

import com.jaewoo.srs.common.code.domain.entity.GroupCode
import com.jaewoo.srs.common.code.domain.entity.QGroupCode
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

interface GroupCodeRepository : JpaRepository<GroupCode, String>, GroupCodeRepositoryCustom {

}