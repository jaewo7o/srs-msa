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
include("services:authentication")
include("services:common")
include("services:api-core")
findProject(":services:api-core")?.name = "api-core"
include("services:api-core")
findProject(":services:api-core")?.name = "api-core"
