package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.dto.UpdateGroupCodeRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupCodeApiController : IGroupCodeApiController {
    override fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable) {
        TODO("Not yet implemented")
    }

    override fun getGroupCode(groupCode: String) {
        TODO("Not yet implemented")
    }

    override fun updateGroupCode(groupCode: String, dto: UpdateGroupCodeRequest) {
        TODO("Not yet implemented")
    }

    override fun createGroupCode(dto: CreateGroupCodeRequest) {
        TODO("Not yet implemented")
    }

    override fun deleteGroupCode(groupCode: String) {
        TODO("Not yet implemented")
    }
}