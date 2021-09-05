package com.jaewoo.srs.common.code.repository

import com.jaewoo.srs.common.code.entity.GroupCode
import org.springframework.data.jpa.repository.JpaRepository

interface GroupCodeRepository : JpaRepository<GroupCode, String>

//@Repository
//class GroupCodeRepositorySupport : QuerydslRepositorySupport(GroupCode::class.java) {
//    fun findAllPage(predicate: Predicate?, pageable: Pageable): Page<GroupCode> {
//        val table = QGroupCode.groupCode1
//        val sql = from(table)
//            .where(predicate)
//
//        val queryResult = querydsl!!.applyPagination(pageable, sql).fetch()
//
//        return PageImpl(queryResult, pageable, sql.fetchCount())
//    }
//}
//
//class GroupCodePredicator {
//    companion object {
//        private val table = QGroupCode.groupCode1
//    }
//
//    private var builder = BooleanBuilder()
//
//    fun name(name: String?): GroupCodePredicator {
//        if (!name.isNullOrBlank()) {
//            builder.and(
//                table.groupCodeNameKo.contains(name)
//                    .or(
//                        table.groupCodeNameEn.contains(name)
//                    )
//            )
//        }
//
//        return this
//    }
//
//    fun value() = builder.value
//}