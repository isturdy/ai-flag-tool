import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val starsectorDirectory = if (System.getProperty("os.name").toLowerCase().contains("windows")) {
    "C:/Program Files (x86)/Fractal Softworks/Starsector"
} else {
    "/Applications/Starsector.app"
}

val jarFileName = "ai-flag-tool.jar"

val starsectorCoreDirectory = if (System.getProperty("os.name").toLowerCase().contains("windows")) {
    "$starsectorDirectory/starsector-core"
} else {
    "$starsectorDirectory/Contents/Resources/Java"
}
val starsectorModDirectory = "$starsectorDirectory/mods"

plugins {
    kotlin("jvm") version "1.3.11"
    java
    jacoco
}

version = "0.3.2"

repositories {
    maven(url = uri("$projectDir/libs"))
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("starfarer:starfarer-api:1.0")
    implementation(fileTree(starsectorCoreDirectory) {
        include("*.jar")
        exclude("starfarer.api.jar")
    })
    implementation(fileTree("$starsectorModDirectory/Console Commands/jars") { include("*.jar") })
    implementation(fileTree("$starsectorModDirectory/LazyLib/jars") { include("*.jar") })
    implementation(fileTree("$starsectorModDirectory/MagicLib/jars") { include("*.jar") })

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.11")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

sourceSets["main"].java {
    srcDir("src/main/java")
}

tasks {
    named<Jar>("jar")
    {
        destinationDir = file("$rootDir/mod")
        archiveName = jarFileName
    }

    register("run-starsector", Exec::class) {
        println("Starting Starsector...")
        workingDir = file(starsectorCoreDirectory)

        commandLine = if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            listOf("cmd", "/C", "starsector.bat")
        } else {
            listOf("$starsectorDirectory/Contents/MacOS/starsector_mac.sh")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// Compile to Java 6 bytecode so that Starsector can use it
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.6"
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}