package com.jaewoo.srs.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RestController




@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
class Application {

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
class HealthCheckController {
    @GetMapping("/ping")
    fun healthCheck(): ResponseEntity<String> {
        println("###health check")
        return ResponseEntity.ok("pong")
    }
}
