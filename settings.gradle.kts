rootProject.name = "srs-msa"

pluginManagement {
    val kotlinPluginVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinPluginVersion
        kotlin("kapt") version kotlinPluginVersion

        kotlin("plugin.jpa") version kotlinPluginVersion
        kotlin("plugin.spring") version kotlinPluginVersion
    }
}

include("services:api-core")
include("services:api-test")
include("services:common")
include("cloud:eureka")
include("cloud:gateway")
