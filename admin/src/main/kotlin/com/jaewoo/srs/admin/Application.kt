package com.jaewoo.srs.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAdminServer
@SpringBootApplication
class Application {

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
