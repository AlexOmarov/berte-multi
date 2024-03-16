plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    application
}

application {
    mainClass.set("ru.somarov.berte.ApplicationKt")
}

project.layout.buildDirectory = File("../.build/server")
val exclusions = project.properties["test_exclusions"].toString().replace("/", ".")

detekt {
    config.setFrom(files("$rootDir/detekt-config.yml"))
    reportsDir = file("${project.layout.buildDirectory.get().asFile.path}/reports/detekt")
}

kover {
    useJacoco()
}

koverReport {
    filters {
        excludes {
            classes(exclusions.split(","))
        }
    }
    defaults {
        xml {
            onCheck = true
        }
        log {
            onCheck = true
        }
        html {
            onCheck = true
        }

        verify {
            rule {
                minBound(50)
            }
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.ktlint)

    implementation(projects.shared)

    implementation(libs.bundles.ktor.server)

    implementation(libs.logback)

    testImplementation(libs.bundles.test)
    testImplementation(libs.ktor.server.tests)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
