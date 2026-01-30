import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
    signing
}

group = "nl.jacobras"
version = "3.1.1"

mavenPublishing {
    publishToMavenCentral()
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
    compileSdk = 36
    namespace = "nl.jacobras.composeactionmenu"

    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk = 23
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

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop") {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    js { browser() }
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.foundation)
            implementation(libs.compose.icons)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.uiToolingPreview)
        }
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        invokeWhenCreated("androidDebug") {
            dependencies {
                implementation(libs.compose.uiTooling)
            }
        }
    }
}

// From https://github.com/gradle/gradle/issues/26091#issuecomment-1722947958
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}

signing {
    setRequired {
        !gradle.taskGraph.allTasks.any { it is PublishToMavenLocal }
    }
}