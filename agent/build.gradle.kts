plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "jvmobservability"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.9")          // SLF4J API
    runtimeOnly("ch.qos.logback:logback-classic:1.4.11")  // Logback
    // JUnit (if you want to keep it)
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}

//tasks.jar {
//    manifest {
//        attributes(
//            "Premain-Class" to "jvmobservability.Agent",
//            "Implementation-Title" to "JVM Observability Agent",
//            "Implementation-Version" to version
//        )
//    }
//}
tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Premain-Class" to "jvmobservability.Agent",
            "Implementation-Title" to "JVM Observability Agent",
            "Implementation-Version" to version
        )
    }
}

tasks.register<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("fatJar") {
    archiveClassifier.set("all")
    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.runtimeClasspath.get())
    manifest.attributes["Premain-Class"] = "jvmobservability.Agent"
}

tasks.build {
    dependsOn("fatJar")
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}
