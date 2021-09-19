package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.domain.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.SearchGroupCodeRequest
import com.jaewoo.srs.common.code.domain.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.service.GroupCodeService
import com.jaewoo.srs.common.constant.CommonConstant.Companion.API_URL
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@Tag(
    name = "Code",
    description = "공통코드 - 그룹코드"
)
@RestController
class GroupCodeApiController(
    private val groupCodeService: GroupCodeService
) {
    companion object {
        const val baseUrl = "$API_URL/group-codes"
    }

    @Operation(summary = "코드그룹 검색")
    @GetMapping(value = [baseUrl])
    fun searchGroupCodesPageable(dto: SearchGroupCodeRequest, pageable: Pageable) =
        groupCodeService.searchGroupCodesPageable(dto, pageable)

    @Operation(summary = "코드그룹 단건 조회")
    @GetMapping(value = ["$baseUrl/{groupCode}"])
    fun getGroupCode(@PathVariable groupCode: String)
        = groupCodeService.getGroupCode(groupCode).toDto()


    @Operation(summary = "코드그룹 단건 수정")
    @PutMapping(value = ["$baseUrl/{groupCode}"])
    fun updateGroupCode(@PathVariable groupCode: String, @RequestBody dto: UpdateGroupCodeRequest)
        = groupCodeService.updateGroupCode(groupCode, dto).toDto()

    @Operation(summary = "코드그룹 신규 생성")
    @PostMapping(value = [baseUrl])
    fun createGroupCode(@RequestBody dto: CreateGroupCodeRequest)
        = groupCodeService.createGroupCode(dto).toDto()


    @Operation(summary = "코드그룹 삭제")
    @DeleteMapping(value = ["$baseUrl/{groupCode}"])
    fun deleteGroupCode(@PathVariable groupCode: String) {
        groupCodeService.deleteGroupCode(groupCode)
    }
}