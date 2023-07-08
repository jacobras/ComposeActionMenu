@file:Suppress("UnstableApiUsage")

import org.gradle.kotlin.dsl.libs

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

android {
    compileSdk = 33

    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

kotlin {
    android {}
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.preview)
                implementation(compose.ui)
                implementation(compose.uiTooling)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.activity)
            }
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.jacobras"
            artifactId = "composeactionmenu"
            version = "2.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}