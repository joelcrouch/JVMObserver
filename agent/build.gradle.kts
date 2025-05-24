plugins {
    java
}

group = "jvmobservability"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Premain-Class" to "jvmobservability.Agent",
            "Implementation-Title" to "JVM Observability Agent",
            "Implementation-Version" to version
        )
    }
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}
