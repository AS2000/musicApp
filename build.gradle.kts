// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" apply false
}

apply {
    from("ktlint.gradle")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Dependencies.androidBuildToolsGradlePlugin)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.hiltGradlePlugin)
        classpath(Dependencies.androidxNavigationSafeArgsGradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
