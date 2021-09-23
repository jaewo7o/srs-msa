import org.springframework.boot.gradle.tasks.run.BootRun

val springDocVersion = "1.5.10"
val queryDslVersion = "4.4.0"
val springCloudVersion = "2020.0.3"


plugins {
    id("org.springframework.boot") version "2.4.3" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false

    // kotlin 관련 버전은 settings.gradle.kts 와 gradle.properties 에 선언
    kotlin("jvm")
    kotlin("kapt")
    // The plugin specifies the following annotations: @Component, @Async, @Transactional, @Cacheable and @SpringBootTest.
    kotlin("plugin.spring") apply false
    // The plugin specifies @Entity, @Embeddable and @MappedSuperclass no-arg annotations automatically.
    kotlin("plugin.jpa") apply false
}

allprojects {
    group = "com.jaewoo.srs"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-spring")

    repositories {
        mavenCentral()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    dependencies {
        // Kotlin Dependency
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // note that the BOM coordinates are wrapped with the "platform" keyword
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

        // jackson library
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // logging
        implementation("org.fusesource.jansi:jansi:1.8")
        implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")


        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    }

    val snippetsDir = file("build/generated-snippets")

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
            dependsOn(processResources) // kotlin 에서 ConfigurationProperties
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        outputs.dir(snippetsDir)
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    val profile = if (project.hasProperty("profile")) project.property("profile") else "local"
    tasks.withType<BootRun> {
        systemProperty("spring.profiles.active", profile!!)
    }

    sourceSets {
        main {
            resources {
                srcDirs(listOf("src/main/resources", "src/main/resources-$profile"))
            }
        }

        test {
            resources {
                srcDirs(listOf("src/main/resources", "src/main/resources-test"))
            }
        }
    }

//    sourceSets {
//        kotlin.sourceSets.register("$buildDir/generated/source/kapt/main")
//    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

project(":cloud:eureka") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

        implementation("org.glassfish.jaxb:jaxb-runtime")
    }
}

project(":cloud:gateway") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

        // Spring Boot Actuator
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        // SpringDoc (API Document)
        implementation("org.springdoc:springdoc-openapi-webflux-ui:$springDocVersion")
    }
}

project(":services:api-core") {
    dependencies {
        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        // SpringDoc (API Document)
        implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

        // Query DSL
        implementation("com.querydsl:querydsl-jpa")
        kapt("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
        annotationProcessor(
            group = "com.querydsl", name = "querydsl-apt", classifier = "jpa"
        )
    }
}

project(":services:api-test") {
    dependencies {
        implementation(project(":services:api-core"))

        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    }
}

project(":services:common") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":services:api-core"))
        testImplementation(project(":services:api-test"))

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

        // SpringDoc (API Document)
        implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

        // Query DSL
        implementation("com.querydsl:querydsl-jpa")
        kapt("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
        annotationProcessor(
            group = "com.querydsl", name = "querydsl-apt", classifier = "jpa"
        )

        // mariadb
        implementation("org.mariadb.jdbc:mariadb-java-client:2.4.1")
    }
}