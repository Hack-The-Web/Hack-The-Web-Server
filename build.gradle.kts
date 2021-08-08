plugins {
    kotlin("jvm") version "1.5.10" apply false
}


subprojects {

    group = "com.hack"
    version = "1.0-SNAPSHOT"

    apply {
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"(kotlin("stdlib"))
        "implementation"("io.insert-koin:koin-core:3.1.2")
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    }
}


