import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    alias(libs.plugins.ksp)
}

//graalvmNative {
//    binaries {
//        named("main") {
//            imageName.set(rootProject.name + "-" + project.version)
//        }
//    }
//}
tasks.withType<Jar> {
    archivesName = rootProject.name
}

val jimmerVersion = libs.versions.jimmer.get()
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(libs.knife4j.openapi3)
    implementation(project(":commons"))
    implementation(project(":model"))
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
    implementation(libs.jjwt.api)
    runtimeOnly(libs.jjwt.impl)
    runtimeOnly(libs.jjwt.jackson)

    implementation("com.github.yitter:yitter-idgenerator:1.0.6")
}
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

