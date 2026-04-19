plugins {
    id("com.fern.script")
    application
}

application {
    mainClass = "com.fern.automate.issue.create.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
