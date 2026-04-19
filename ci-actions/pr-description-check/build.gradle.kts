plugins {
    id("com.fern.script")
    application
}

application {
    mainClass = "com.fern.automate.pr.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
