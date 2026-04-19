plugins {
    id("com.fern.feature")
    id("com.fern.room")
}

android {
    namespace = "com.fern.data.testing"
}

dependencies {
    implementation(projects.shared.data.core)
}
