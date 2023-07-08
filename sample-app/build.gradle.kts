@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    defaultConfig {
        applicationId = "nl.jacobras.composeactionmenu"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // BoMs
    implementation(platform(libs.compose.bom))

    // The actual library
    implementation(project(":compose-action-menu"))

    implementation(libs.compose.activity)
    implementation(libs.compose.compiler)
    implementation(libs.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.android.test.junit)
}