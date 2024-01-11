import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    alias(libs.plugins.ksp)
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set(rootProject.name + "-" + project.version)
        }
    }
}
tasks.withType<Jar> {
    archivesName = rootProject.name
}

val jimmerVersion = libs.versions.jimmer.get()
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(libs.knife4j.openapi3)
    implementation(project(":commons"))
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
}
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

