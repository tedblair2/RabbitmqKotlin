plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.github.tedblair2"
version = "1.0.0"

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("com.rabbitmq:amqp-client:5.20.0")
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
        implementation("ch.qos.logback:logback-classic:1.4.11")
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain(11)
    }
}

