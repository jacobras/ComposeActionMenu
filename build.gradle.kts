plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
    kotlin("multiplatform") version libs.versions.kotlin apply false
    kotlin("android") version libs.versions.kotlin apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
}