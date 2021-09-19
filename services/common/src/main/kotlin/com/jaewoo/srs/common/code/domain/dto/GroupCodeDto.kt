package com.jaewoo.srs.common.code.domain.dto

import com.jaewoo.srs.common.code.domain.entity.GroupCode
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(description = "그룹코드 생성요청")
data class CreateGroupCodeRequest(
    @field:NotBlank
    val groupCode: String,

    @field:NotBlank
    @field:Size(min = 0, max = 100)
    val groupCodeNameKo: String,

    val groupCodeNameEn: String
) {
    fun toEntity() = GroupCode(
        groupCode = this.groupCode,
        groupCodeNameKo = this.groupCodeNameKo,
        groupCodeNameEn = this.groupCodeNameEn
    )
}

@Schema(description = "그룹코드 수정요청")
data class UpdateGroupCodeRequest(
    @Schema(name = "그룹코드 한글명", required = true, example = "메시지분류")
    @field:NotBlank
    val groupCodeNameKo: String,

    @Schema(name = "그룹코드 영문명", required = false, example = "Message Type")
    val groupCodeNameEn: String
)

@Schema(description = "그룹코드 검색")
data class SearchGroupCodeRequest(
    @Schema(name = "검색어")
    var name: String?
)

data class GroupCodeResponse (
    val groupCode: String,
    val groupCodeNameKo: String,
    val groupCodeNameEn: String
)