@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ComposeActionMenu"
include(":sample-app")
include(":sample-desktop")
include(":sample-web")
include(":compose-action-menu")