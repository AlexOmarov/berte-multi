plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.sonarqube)
}

var exclusions = project.properties["test_exclusions"].toString()

sonar {
    properties {
        property("sonar.qualitygate.wait", "true")
        property("sonar.core.codeCoveragePlugin", "jacoco")
        property(
            "sonar.kotlin.detekt.reportPaths",
            "${project(":server:app").layout.buildDirectory.get().asFile}/reports/detekt/detekt.xml, " +
                    "${project(":server:api").layout.buildDirectory.get().asFile}/reports/detekt/detekt.xml" +
                    "${project(":native").layout.buildDirectory.get().asFile}/reports/detekt/detekt.xml" +
                    "${project(":shared").layout.buildDirectory.get().asFile}/reports/detekt/detekt.xml" +
                    "${project(":composeApp").layout.buildDirectory.get().asFile}/reports/detekt/detekt.xml"
        )
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project(":server:app").layout.buildDirectory.get().asFile}/reports/kover/report.xml"
        )
        property("sonar.cpd.exclusions", exclusions)
        property("sonar.jacoco.excludes", exclusions)
        property("sonar.coverage.exclusions", exclusions)
    }
}