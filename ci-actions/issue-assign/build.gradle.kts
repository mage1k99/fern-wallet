plugins {
    id("com.fern.script")
    application
}

application {
    mainClass = "com.fern.automate.issue.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
