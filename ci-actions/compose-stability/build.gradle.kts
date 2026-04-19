plugins {
    id("com.fern.script")
    application
}

application {
    mainClass = "com.fern.automate.compose.stability.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
