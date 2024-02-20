import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.detekt)
}
project.layout.buildDirectory = File("../.build/api")

detekt {
    config.setFrom(files("$rootDir/detekt-config.yml"))
    reportsDir = file("${project.layout.buildDirectory.get().asFile.path}/reports/detekt")
}

dependencies {
    detektPlugins(libs.detekt.ktlint)
}
