package com.jaewoo.srs.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
@ComponentScan("com.jaewoo.srs")
@SpringBootApplication
class Application {
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}