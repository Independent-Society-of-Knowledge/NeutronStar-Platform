import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "core.MainKt"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

//     Lets-Plot Kotlin API
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

//tasks.register("main") {
//    group = "build"
//    description = "Runs the main.kt class"
//    doLast {
//        // Replace `mainClassName` with the fully qualified name of your SS.kt class (e.g., com.example.yourpackage.SS)
//        javaexec {
//            main = "org.example.core.main"
//
//        }
//    }
//}