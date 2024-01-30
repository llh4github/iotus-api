plugins {
    id("kotlin-conventions")
    alias(libs.plugins.ksp)
}

val jimmerVersion: String = libs.versions.jimmer.get()
val springbootVersion = libs.versions.springBoot.get()

dependencies {
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
    implementation("org.babyfish.jimmer:jimmer-core:${jimmerVersion}")
    compileOnly("org.babyfish.jimmer:jimmer-sql-kotlin:${jimmerVersion}")
    compileOnly(libs.swagger.annotations)

    compileOnly("org.springframework.boot:spring-boot-starter:${springbootVersion}")
    compileOnly("org.springframework.boot:spring-boot-starter-validation:${springbootVersion}")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jdbc:${springbootVersion}")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
