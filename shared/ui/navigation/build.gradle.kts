plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.navigation"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
    implementation(projects.shared.ui.core)
}
