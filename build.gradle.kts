plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
    kotlin("multiplatform") version libs.versions.kotlin apply false
    kotlin("android") version libs.versions.kotlin apply false
    alias(libs.plugins.android.application) version libs.versions.agp apply false
    alias(libs.plugins.android.library) version libs.versions.agp apply false
    alias(libs.plugins.compose.multiplatform) version libs.versions.compose.multiplatform apply false
}