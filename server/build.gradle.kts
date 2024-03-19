plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

application {
    mainClass.set("ru.somarov.berte.ApplicationKt")
}

val exclusions = project.properties["test_exclusions"].toString().replace("/", ".")

dependencies {
    implementation(projects.shared)

    implementation(libs.bundles.ktor.server)

    implementation(libs.logback)

    testImplementation(libs.bundles.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.junit.api)

    testRuntimeOnly(libs.junit.engine)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
