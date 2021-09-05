package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.dto.GroupCodeResponse
import com.jaewoo.srs.common.code.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.service.GroupCodeService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupCodeApiController(
    private val groupCodeService: GroupCodeService
) : IGroupCodeApiController {
//    override fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable) =
//        groupCodeService.searchGroupCodesPageable(dto, pageable)

    override fun getGroupCode(groupCode: String): GroupCodeResponse {
        val groupCodeEntity = groupCodeService.getGroupCode(groupCode)
        return GroupCodeResponse (
            groupCodeEntity.groupCode,
            groupCodeEntity.groupCodeNameKo,
            groupCodeEntity.groupCodeNameEn
        )
    }

    override fun updateGroupCode(groupCode: String, dto: UpdateGroupCodeRequest): GroupCodeResponse {
        val groupCodeEntity = groupCodeService.updateGroupCode(groupCode, dto)
        return GroupCodeResponse (
            groupCodeEntity.groupCode,
            groupCodeEntity.groupCodeNameKo,
            groupCodeEntity.groupCodeNameEn
        )
    }

    override fun createGroupCode(dto: CreateGroupCodeRequest): GroupCodeResponse {
        val groupCodeEntity = groupCodeService.createGroupCode(dto)
        return GroupCodeResponse (
            groupCodeEntity.groupCode,
            groupCodeEntity.groupCodeNameKo,
            groupCodeEntity.groupCodeNameEn
        )
    }

    override fun deleteGroupCode(groupCode: String) {
        groupCodeService.deleteGroupCode(groupCode)
    }
}