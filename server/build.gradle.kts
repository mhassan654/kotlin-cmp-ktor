plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "3.0.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth.jvm)
    implementation(libs.ktor.server.auth.jwt.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.host.common.jvm)
    implementation(libs.ktor.server.status.pages.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.server.swagger.jvm)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
    testImplementation(libs.ktor.server.test.host.jvm)
    testImplementation(libs.kotlin.test.junit)

    //database
    implementation(libs.exposed.core)
    implementation(libs.exposed.crypt)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.postgresql)


    // optional jodatime
//    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
//    // optional java-time
//    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
//    // optional kotlin-datetime
//    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
//    // optional json
//    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
//    // optional money
//    implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
}
