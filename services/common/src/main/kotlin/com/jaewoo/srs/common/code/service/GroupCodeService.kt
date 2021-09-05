package com.jaewoo.srs.common.code.service

import com.jaewoo.srs.common.code.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.entity.GroupCode
import com.jaewoo.srs.common.code.repository.GroupCodeRepository
import com.jaewoo.srs.core.exception.SrsDataNotFoundException
import com.jaewoo.srs.core.exception.SrsRuntimeException
import org.springframework.stereotype.Service

@Service
class GroupCodeService(
    private val groupCodeRepository: GroupCodeRepository
//    private val groupCodeRepositorySupport: GroupCodeRepositorySupport
) {
/*    fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable): Any {
//        val predicate = GroupCodePredicator()
//            .name(dto.name)
//            .value()
//
//        return groupCodeRepositorySupport.findAllPage(predicate, pageable)
    }*/

    fun getGroupCode(groupCode: String)
            = groupCodeRepository.findById(groupCode).orElseThrow { SrsDataNotFoundException() }

    fun updateGroupCode(groupCode: String, dto: UpdateGroupCodeRequest): GroupCode {
        val groupCodeEntity = getGroupCode(groupCode).also {
            it.groupCodeNameKo = dto.groupCodeNameKo
            it.groupCodeNameEn = dto.groupCodeNameEn
        }

        return groupCodeRepository.save(groupCodeEntity)
    }

    fun createGroupCode(dto: CreateGroupCodeRequest): GroupCode {
        val isExist = groupCodeRepository.existsById(dto.groupCode)
        if (isExist) {
            throw SrsRuntimeException("MSG0006")
        }

        val groupCode = GroupCode (
            dto.groupCode,
            dto.groupCodeNameKo,
            dto.groupCodeNameEn
        )

        return groupCodeRepository.save(groupCode)
    }

    fun deleteGroupCode(groupCode: String) {
        groupCodeRepository.deleteById(groupCode)
    }
}