import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":compose-action-menu"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "example.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Desktop sample"
            packageVersion = "1.0.0"

            val iconsRoot = project.file("../common/src/desktopMain/resources/images")
            macOS {
                iconFile.set(iconsRoot.resolve("icon-mac.icns"))
            }
            windows {
                iconFile.set(iconsRoot.resolve("icon-windows.ico"))
                menuGroup = "Compose Examples"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
            }
            linux {
                iconFile.set(iconsRoot.resolve("icon-linux.png"))
            }
        }
    }
}