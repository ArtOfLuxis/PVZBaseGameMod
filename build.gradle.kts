plugins {
    kotlin("jvm") version "2.3.10"
    kotlin("plugin.serialization") version "2.3.10"
    kotlin("multiplatform") version "2.3.10" apply false
    id("com.soywiz.korge") version "6.0.0" apply false
}

// change to something else, and change package names in src/main/kotlin to the new group
val groupName = "me.artofluxis"

group = groupName
version = "1.0"

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation("com.soywiz.korge:korge-jvm:6.0.0")
    implementation(files("libs/PVZ-jvm.jar"))
}

kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "$groupName.ModInitializer"
        )
    }
}