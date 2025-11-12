import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//plugins {
//    kotlin("jvm") version "2.2.20"
//}
plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.mockk:mockk:1.13.5") // optional
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

//dependencies {
//    testImplementation(kotlin("test"))
//}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}