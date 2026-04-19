plugins {
    id("com.fern.feature")
}

android {
    namespace = "com.fern.data.model.testing"
}

dependencies {
    implementation(projects.shared.data.model)

    implementation(libs.bundles.testing)
}