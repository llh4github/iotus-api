plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(libs.knife4j.openapi3)
    implementation(libs.yitter.idgenerator)
    runtimeOnly("org.postgresql:postgresql")
    implementation(libs.jimmer.starter)
    ksp(libs.jimmer.ksp)

    implementation(project(":mod-commons"))
    implementation(project(":mod-dal"))
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
