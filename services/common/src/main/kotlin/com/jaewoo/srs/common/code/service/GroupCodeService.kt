package com.jaewoo.srs.common.code.service

import com.jaewoo.srs.common.code.domain.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.domain.entity.GroupCode
import com.jaewoo.srs.common.code.repository.GroupCodeRepository
import com.jaewoo.srs.core.exception.SrsDataNotFoundException
import com.jaewoo.srs.core.exception.SrsRuntimeException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GroupCodeService(
    private val groupCodeRepository: GroupCodeRepository
) {
    fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable): Any {
        return groupCodeRepository.findAllPage(dto, pageable)
    }

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

        return groupCodeRepository.save(dto.toEntity())
    }

    fun deleteGroupCode(groupCode: String) {
        groupCodeRepository.deleteById(groupCode)
    }
}