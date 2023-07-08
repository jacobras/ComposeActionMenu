@file:Suppress("UnstableApiUsage")

import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.libs

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
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
    implementation(libs.compose.activity)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.preview)
    implementation(compose.ui)
    implementation(compose.uiTooling)
}