import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 36
    namespace = "nl.jacobras.composeactionmenu.sample"

    buildFeatures {
        compose = true
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt")))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        applicationId = "nl.jacobras.composeactionmenu"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    // The actual library
    implementation(project(":compose-action-menu"))

    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.icons)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
}