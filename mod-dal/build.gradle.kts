plugins {
    id("kotlin-conventions")
    alias(libs.plugins.ksp)
    id("spring-conventions")
    alias(libs.plugins.jimmer.gradle)
}

dependencies {
    implementation(libs.yitter.idgenerator)
    compileOnly(libs.jimmer.core)
    compileOnly(libs.jimmer.kotlin)
    ksp(libs.jimmer.ksp)
    implementation(project(":mod-commons"))

    compileOnly("org.springframework.boot:spring-boot-starter")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jdbc")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
