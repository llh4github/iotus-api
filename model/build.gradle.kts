plugins {
    id("kotlin-conventions")
    alias(libs.plugins.ksp)
}

val jimmerVersion: String = libs.versions.jimmer.get()
dependencies {
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
    implementation("org.babyfish.jimmer:jimmer-core:${jimmerVersion}")
    compileOnly("org.babyfish.jimmer:jimmer-sql-kotlin:${jimmerVersion}")
    compileOnly(libs.swagger.annotations)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
normalization{
    runtimeClasspath{
        ignore("**/*.dto")
    }
}

