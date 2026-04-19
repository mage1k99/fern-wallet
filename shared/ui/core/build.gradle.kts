plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.ui"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
}