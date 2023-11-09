import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Database Construction
    implementation( "org.jetbrains.exposed:exposed-core:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-crypt:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-dao:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-java-time:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-money:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-json:0.44.1")
    implementation ("org.jetbrains.exposed:exposed-spring-boot-starter:0.44.1")

    // Plotting
    // Lets-Plot Kotlin API
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.4.3")
    // Lets-Plot Multiplatform (Batik rendering)
    implementation("org.jetbrains.lets-plot:lets-plot-batik:4.0.1")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:4.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
