package com.jaewoo.srs.common

import com.jaewoo.srs.core.security.properties.SecurityProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan("com.jaewoo.srs")
@EnableConfigurationProperties(SecurityProperties::class)
@SpringBootApplication
class Application {
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}