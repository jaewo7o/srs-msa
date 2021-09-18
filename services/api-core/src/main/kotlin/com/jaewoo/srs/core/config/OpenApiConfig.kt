package com.jaewoo.srs.core.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class OpenApiConfig {

    @Value("\${springdoc.version}")
    lateinit var appVersion: String

    @Bean
    fun openAPI() : OpenAPI {
        val info = Info().title("SRS REST API").version(appVersion)
            .description("Spring Boot를 이용한 Demo 웹 애플리케이션 REST API입니다.")
            .termsOfService("http://swagger.io/terms/")
            .contact(
                    Contact().apply {
                        name = "Jaewoo"
                        url = "https://www.samsung.com/"
                        email = "jeawoo.jeong@gmail.com"
                    }
            )
            .license(License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return OpenAPI()
            .components(Components())
            .info(info);
    }
}