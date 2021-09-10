package com.jaewoo.srs.common.code.builder

import com.jaewoo.srs.common.code.domain.dto.CreateGroupCodeRequest
import com.jaewoo.srs.common.code.domain.entity.GroupCode

fun buildCreateGroupCodeRequest(
    groupCode: String = "groupCode",
    groupCodeNameKo: String = "groupCodeNameKo",
    groupCodeNameEn: String = "groupCodeNameEn"
) = CreateGroupCodeRequest(
    groupCode = groupCode,
    groupCodeNameKo = groupCodeNameKo,
    groupCodeNameEn = groupCodeNameEn
)

fun buildGroupCode(
    groupCode: String = "groupCode",
    groupCodeNameKo: String = "groupCodeNameKo",
    groupCodeNameEn: String = "groupCodeNameEn"
) = GroupCode(
    groupCode = groupCode,
    groupCodeNameKo = groupCodeNameKo,
    groupCodeNameEn = groupCodeNameEn
)