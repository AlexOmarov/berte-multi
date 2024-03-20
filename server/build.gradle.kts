import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ktor)
    application
}

kotlin {
    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass.set("ru.somarov.berte.ApplicationKt")
        }
    }
    sourceSets {
        jvmMain.dependencies {
            implementation(projects.shared)
            implementation(libs.bundles.ktor.server)
            implementation(libs.logback)
        }
        jvmTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.kotlin.test.junit)
            implementation(libs.junit.api)

            runtimeOnly(libs.junit.engine)
        }
    }
}

application {
    mainClass.set("ru.somarov.berte.ApplicationKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
