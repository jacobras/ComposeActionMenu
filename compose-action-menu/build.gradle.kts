import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
}

group = "nl.jacobras"
version = "3.0.0"

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01, true)
    signAllPublications()

    pom {
        name.set("Compose Action Menu")
        description.set("An easy to use action/overflow menu for Jetpack Compose")
        url.set("https://github.com/jacobras/composeactionmenu")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("jacobras")
                name.set("Jacob Ras")
                email.set("info@jacobras.nl")
            }
        }
        scm {
            url.set("https://github.com/jacobras/ComposeActionMenu")
        }
    }
}

android {
    compileSdk = 35
    namespace = "nl.jacobras.composeactionmenu"

    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        publishLibraryVariants("release")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop")
    js { browser() }
    wasmJs { browser() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.uiTooling)
            }
        }
    }

    jvmToolchain(17)
}

// From https://github.com/gradle/gradle/issues/26091#issuecomment-1722947958
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}