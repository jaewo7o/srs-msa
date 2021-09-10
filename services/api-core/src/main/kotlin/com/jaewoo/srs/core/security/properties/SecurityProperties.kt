package com.jaewoo.srs.core.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

// reference site : https://towardsdatascience.com/a-guide-to-use-spring-boots-configurationproperties-annotation-in-kotlin-s-dataclass-1341c63110f4
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt-security")
data class SecurityProperties (
    var secretKey: String,

    var accessTokenExpiration: String,
    var refreshTokenExpiration: String,

    val accessTokenHeader: String,
    val RefreshTokenHeader: String
)