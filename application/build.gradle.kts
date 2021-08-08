plugins {
    application
}

application {
    mainClass.set("com.hack.app.Application")
}

dependencies {
    implementation(project(":game"))
    implementation(project(":game-api"))
    implementation(project(":network"))
    implementation(project(":network-api"))
}