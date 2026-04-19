plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.features"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
    implementation(projects.shared.ui.core)
    implementation(projects.shared.ui.core)
    implementation(projects.shared.ui.navigation)
}