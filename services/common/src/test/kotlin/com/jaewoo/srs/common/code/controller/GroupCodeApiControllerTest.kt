package com.jaewoo.srs.common.code.controller

import com.jaewoo.srs.common.code.builder.buildCreateGroupCodeRequest
import com.jaewoo.srs.common.code.builder.buildGroupCode
import com.jaewoo.srs.common.code.domain.dto.UpdateGroupCodeRequest
import com.jaewoo.srs.common.code.repository.GroupCodeRepository
import com.jaewoo.srs.common.constant.CommonConstant.Companion.API_URL
import com.jaewoo.srs.core.exception.SrsDataNotFoundException
import com.jaewoo.srs.core.test.SpringWebTestSupport
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.transaction.annotation.Transactional

internal class GroupCodeApiControllerTest (
    private val groupCodeRepository: GroupCodeRepository
) : SpringWebTestSupport() {

    val baseUrl = "${API_URL}/group-codes"

    @Test
    @Transactional
    fun `그룹코드 검색 - 페이지`() {
        // given
        val searchName = "NAMEKO5"

        (1..10).map {
            buildGroupCode(
                groupCode = "CODE${it}",
                groupCodeNameKo = "NAMEKO${it}",
                groupCodeNameEn = "NAMEEN${it}"
            )
        }.also {
            saveAll(it)
        }

        val pageSize = 5
        val pageNumber = 0

        // when & then
        mockMvc.get(baseUrl) {
            param("page", pageNumber.toString())
            param("size", pageSize.toString())
            param("name", searchName)
        }.andExpect {
            status { isOk() }
            jsonPath("$..size") { value(pageSize) }
            jsonPath("$..number") { value(pageNumber) }
            jsonPath("$..numberOfElements") { value(1) }
        }.andDo { print() }
    }

    @Test
    @Transactional
    fun `그룹코드 단건 조회`() {
        // given
        val saveGroupCode = save(buildGroupCode())

        // when & then
        mockMvc.get("${baseUrl}/${saveGroupCode.groupCode}")
            .andExpect {
                status { isOk() }
                jsonPath("$..groupCode") { value(saveGroupCode.groupCode) }
                jsonPath("$..groupCodeNameKo") { value(saveGroupCode.groupCodeNameKo) }
                jsonPath("$..groupCodeNameEn") { value(saveGroupCode.groupCodeNameEn) }
            }.andDo { print() }
    }

    @Test
    @Transactional
    fun `그룹코드 단건 수정`() {
        // given
        val beforeGroupCode = save(buildGroupCode())
        val dto = UpdateGroupCodeRequest(
            groupCodeNameKo = beforeGroupCode.groupCodeNameKo + "-수정",
            groupCodeNameEn = beforeGroupCode.groupCodeNameEn + "-수정"
        )

        // when
        mockMvc.put("${baseUrl}/${beforeGroupCode.groupCode}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
        }.andExpect {
            status { isOk() }
        }.andDo { print() }

        // then
        val saveGroupCode =
            groupCodeRepository.findById(beforeGroupCode.groupCode).orElseThrow { SrsDataNotFoundException() }

        Assertions.assertThat(saveGroupCode.groupCodeNameKo).isEqualTo(dto.groupCodeNameKo)
        Assertions.assertThat(saveGroupCode.groupCodeNameEn).isEqualTo(dto.groupCodeNameEn)
    }

    @Test
    @Transactional
    fun `그룹코드 생성`() {
        // given
        val dto = buildCreateGroupCodeRequest()

        // when
        mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
        }.andExpect {
            status { isOk() }
        }.andDo {
            print()
        }

        // then
        val saveGroupCode = groupCodeRepository.findById(dto.groupCode).orElseThrow { SrsDataNotFoundException() }
        Assertions.assertThat(saveGroupCode.groupCodeNameKo).isEqualTo(dto.groupCodeNameKo)
        Assertions.assertThat(saveGroupCode.groupCodeNameEn).isEqualTo(dto.groupCodeNameEn)
    }
}