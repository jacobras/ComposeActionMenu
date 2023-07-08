@file:Suppress("UnstableApiUsage")

import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.libs

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}

dependencies {
    // BoMs
    implementation(platform(libs.compose.bom))

    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
}