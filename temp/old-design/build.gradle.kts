plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.design"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.ui.core)

    implementation(projects.shared.domain)
}