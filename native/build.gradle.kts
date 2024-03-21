plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    mingwX64().apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    linuxX64().apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        mingwMain.dependencies {
            implementation(projects.shared)
        }
        linuxMain.dependencies {
            implementation(projects.shared)
        }
    }
}
