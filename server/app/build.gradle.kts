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

project.layout.buildDirectory = File("../.build/app")
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
    implementation(projects.server.api)

    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    implementation("io.ktor:ktor-server-websockets-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-metrics-micrometer-jvm")
    implementation("io.ktor:ktor-server-metrics-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-call-id-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-openapi")
    implementation("io.ktor:ktor-server-config-yaml:2.3.8")

    testImplementation(libs.bundles.test)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}
repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
