group = "com.jaewoo.srs.api"
version = "1.0-SNAPSHOT"

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
