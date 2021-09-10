package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.domain.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.GroupCodeResponse
import com.jaewoo.srs.common.code.domain.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.service.GroupCodeService
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
class GroupCodeApiController(
    private val groupCodeService: GroupCodeService
) {
    companion object {
        const val baseUrl = "/api/group-codes"
        const val aBaseUrl = "/api/anonymous/group-codes"
    }

    @ApiOperation(value = "코드그룹 검색")
    @GetMapping(value = [aBaseUrl, baseUrl])
    fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable) =
        groupCodeService.searchGroupCodesPageable(dto, pageable)

    @ApiOperation(value = "코드그룹 단건 조회")
    @GetMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun getGroupCode(@PathVariable groupCode: String)
        = groupCodeService.getGroupCode(groupCode).toDto()


    @ApiOperation(value = "코드그룹 단건 수정")
    @PutMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun updateGroupCode(@PathVariable groupCode: String, @RequestBody dto: UpdateGroupCodeRequest)
        = groupCodeService.updateGroupCode(groupCode, dto).toDto()

    @ApiOperation(value = "코드그룹 신규 생성")
    @PostMapping(value = [aBaseUrl, baseUrl])
    fun createGroupCode(@RequestBody dto: CreateGroupCodeRequest)
        = groupCodeService.createGroupCode(dto).toDto()


    @ApiOperation(value = "코드그룹 삭제")
    @DeleteMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun deleteGroupCode(@PathVariable groupCode: String) {
        groupCodeService.deleteGroupCode(groupCode)
    }
}