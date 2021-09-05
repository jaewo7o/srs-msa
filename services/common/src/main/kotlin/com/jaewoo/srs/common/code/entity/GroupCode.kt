package com.jaewoo.srs.common.code.entity

import com.jaewoo.srs.core.jpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "group_code")
@Entity
class GroupCode(
    @Id
    @Column(name = "groupCode", nullable = false)
    var groupCode: String,

    @Column(name = "groupCodeNameKo", nullable = false)
    var groupCodeNameKo: String,

    @Column(name = "groupCodeNameEn")
    var groupCodeNameEn: String
) : BaseEntity()