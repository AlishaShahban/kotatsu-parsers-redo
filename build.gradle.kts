plugins {
    `java-library`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm")
    id("com.google.devtools.ksp")
}

// import tasks.ReportGenerateTask

group = "org.koitharu"
version = "1.0"

/*
tasks.test {
    useJUnitPlatform()
}
*/
ksp {
    arg("summaryOutputDir", "${projectDir}/.github")
}

/*
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.contracts.ExperimentalContracts",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=org.koitharu.kotatsu.parsers.InternalParsersApi",
        )
    }
}
*/

kotlin {
    jvmToolchain(17)
    explicitApiWarning()
    sourceSets["main"].kotlin.srcDirs("build/generated/ksp/main/kotlin")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okio:okio:3.6.0")
    implementation("org.json:json:20231013")
    implementation("androidx.collection:collection:1.3.0")
    api("org.jsoup:jsoup:1.17.2")

    ksp(project(":kotatsu-parsers-ksp"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("app.cash.quickjs:quickjs-android:0.9.2")
}

// tasks.register<ReportGenerateTask>("generateTestsReport")
