@file:Suppress("UnstableApiUsage")

rootProject.name = "berte-multi"

include(":composeApp")
include(":server")
include(":server:app")
include(":server:api")
include(":shared")
include(":native")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}