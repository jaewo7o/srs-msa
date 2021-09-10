package com.jaewoo.srs.core.context

data class SessionUser(
    val id: Long,
    val name: String,
    val mobileNo: String,
    val languageCode: String,
    val timezoneName: String,
    val loginId: String,
    val password: String
)
