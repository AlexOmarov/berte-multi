plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ktor)
    application
}

kotlin {
    jvm()
}

dependencies {
    implementation(projects.shared)

    implementation(libs.bundles.ktor.server)

    implementation(libs.logback)

    testImplementation(libs.bundles.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.junit.api)

    testRuntimeOnly(libs.junit.engine)
}

application {
    mainClass.set("ru.somarov.berte.ApplicationKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
