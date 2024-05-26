plugins {
    id("kotlin-conventions")
    `java-library`
}

dependencies {
    api(libs.swagger.annotations)
    compileOnly(libs.slf4j.api)
    compileOnly(libs.logback.core)
    compileOnly(libs.logback.classic)
}
