package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.dto.UpdateGroupCodeRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@Api(
    tags = ["GroupCode"],
    description = "공통코드 - 그룹코드"
)
interface IGroupCodeApiController {
    companion object {
        const val baseUrl = "/api/group-codes"
        const val aBaseUrl = "/api/anonymous/group-codes"
    }

    @ApiOperation(value = "코드그룹 검색")
    @GetMapping(value = [aBaseUrl, baseUrl])
    fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable)

    @ApiOperation(value = "코드그룹 단건 조회")
    @GetMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun getGroupCode(@PathVariable groupCode: String)


    @ApiOperation(value = "코드그룹 단건 수정")
    @PutMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun updateGroupCode(@PathVariable groupCode: String, @RequestBody dto: UpdateGroupCodeRequest)

    @ApiOperation(value = "코드그룹 신규 생성")
    @PostMapping(value = [aBaseUrl, baseUrl])
    fun createGroupCode(@RequestBody dto: CreateGroupCodeRequest)

    @ApiOperation(value = "코드그룹 삭제")
    @DeleteMapping(value = ["$aBaseUrl/{groupCode}", "$baseUrl/{groupCode}"])
    fun deleteGroupCode(@PathVariable groupCode: String)
}