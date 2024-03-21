plugins {
    // this is necessary to avoid the plugins to be loaded multiple times in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktor) apply false

    alias(libs.plugins.detekt)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.kover)
}

val exclusions = project.properties["testExclusions"].toString()
val buildDir: String = project.layout.buildDirectory.get().asFile.path

dependencies {
    detektPlugins(libs.detekt.ktlint)
    kover(project(":shared"))
    kover(project(":server"))
    kover(project(":composeApp"))
}

detekt {
    config.from(files("$rootDir/detekt-config.yml"))
    source.from("shared", "native", "server", "composeApp")
    reportsDir = file("$buildDir/reports/detekt")
}

kover {
    useJacoco()
}

koverReport {
    filters {
        excludes {
            classes(exclusions.replace("/", ".").split(","))
        }
    }
    defaults {
        xml { onCheck = true }
        log { onCheck = true }

        verify {
            rule {
                minBound(0)
            }
        }
    }
}

sonar {
    properties {
        property("sonar.qualitygate.wait", "true")
        property("sonar.gradle.skipCompile", "true")
        property("sonar.core.codeCoveragePlugin", "jacoco")
        property("sonar.kotlin.detekt.reportPaths", "$buildDir/reports/detekt/detekt.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/kover/report.xml")
        property("sonar.cpd.exclusions", exclusions)
        property("sonar.jacoco.excludes", exclusions)
        property("sonar.coverage.exclusions", exclusions)
    }
}
