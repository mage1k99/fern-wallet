plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.disclaimer"
}

dependencies {
    implementation(projects.shared.data.core)
    implementation(projects.shared.ui.core)
    implementation(projects.shared.ui.navigation)

    testImplementation(projects.shared.ui.testing)
}
