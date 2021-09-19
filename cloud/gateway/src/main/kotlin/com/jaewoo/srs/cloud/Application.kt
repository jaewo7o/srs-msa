package com.jaewoo.srs.cloud

import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan


@EnableDiscoveryClient
@ComponentScan("com.jaewoo.srs")
@SpringBootApplication
class Application {

    @Autowired
    var locator: RouteDefinitionLocator? = null

    @Bean
    fun apis(): List<GroupedOpenApi>? {
        var groups : MutableList<GroupedOpenApi> = mutableListOf()
        val definitions = locator!!.routeDefinitions.collectList().block()!!
        println("####### ${definitions}")
        definitions.stream()
            .filter { routeDefinition: RouteDefinition ->
                routeDefinition.id.matches(Regex(".*-service"))
            }
            .forEach { routeDefinition: RouteDefinition ->
                val name = routeDefinition.id.replace("-service".toRegex(), "")
                println("### name : $name")

                groups.add(
                    GroupedOpenApi.builder()
                        .pathsToMatch("/$name/**")
                        .group(name)
                        .build()
                )
            }

        return groups
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}