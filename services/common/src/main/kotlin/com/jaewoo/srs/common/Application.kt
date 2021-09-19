package com.jaewoo.srs.common

import com.jaewoo.srs.core.logging.Log
import com.jaewoo.srs.core.security.properties.SecurityProperties
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@OpenAPIDefinition(
    info = Info(title = "Common API", version = "1.0.0", description = "Documentation Common REST API v1.0.0")
)
@EnableDiscoveryClient
@EnableConfigurationProperties(SecurityProperties::class)
@SpringBootApplication(scanBasePackages = ["com.jaewoo.srs"])
class Application {
    companion object : Log

    @Value("\${spring.profiles.active}")
    lateinit var profile: String

    @Bean
    fun databaseInitializer() = CommandLineRunner {
        logger.info("current active profile : $profile")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}